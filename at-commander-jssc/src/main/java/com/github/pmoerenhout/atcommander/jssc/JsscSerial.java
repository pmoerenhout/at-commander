package com.github.pmoerenhout.atcommander.jssc;

import static jssc.SerialPort.PURGE_RXCLEAR;
import static jssc.SerialPort.PURGE_TXCLEAR;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.Mode;
import com.github.pmoerenhout.atcommander.api.ReadThread;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.SolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.common.Util;
import jssc.SerialPort;
import jssc.SerialPortException;

public class JsscSerial implements SerialInterface {

  private static final Logger LOG = LoggerFactory.getLogger(JsscSerial.class);

  private final List<UnsolicitedPatternClass> unsolicitedPatterns;
  private final UnsolicitedResponseCallback unsolicitedResponseCallback;
  private final String port;
  private final int speed;
  private final int flowControlMode;
  Mode mode;
  Pipe pipe;
  Pipe outputPipe;
  InputStream inputStream;
  OutputStream outputStream;
  List<String> lines;
  private SerialPort serialPort;
  private String id;
  private SolicitedResponseCallback solicitedResponseCallback;
  private ReadThread readThread;

  public JsscSerial(final String port, final int speed, final int flowControlMode, final UnsolicitedResponseCallback unsolicitedResponseCallback) {
    this.id = UUID.randomUUID().toString();
    this.port = port;
    this.speed = speed;
    this.flowControlMode = flowControlMode;
    this.unsolicitedResponseCallback = unsolicitedResponseCallback;
    this.unsolicitedPatterns = new ArrayList<>();
    LOG.debug("Construct JsscSerial with id {}", this.id);
  }

  private byte[] readBytes(final ByteBuffer byteBuffer) {
    // Buffer must be flipped after writing
    final byte[] bytesArray = new byte[byteBuffer.remaining()];
    byteBuffer.get(bytesArray, byteBuffer.position(), bytesArray.length);
    return bytesArray;
  }

  public String getId() {
    return id;
  }

  public String getPort() {
    return port;
  }

  public int getSpeed() {
    return speed;
  }

  public int getFlowControlMode() {
    return flowControlMode;
  }

  public void addUnsolicited(final UnsolicitedPatternClass unsolicitedPatternClass) {
    this.unsolicitedPatterns.add(unsolicitedPatternClass);
    LOG.trace("Added Unsolicited {} - {}", unsolicitedPatternClass.getClazz().getName(), unsolicitedPatternClass.getPattern().toString());
  }

  public void setSolicitedResponseCallback(final SolicitedResponseCallback solicitedResponseCallback) {
    this.solicitedResponseCallback = solicitedResponseCallback;
  }

  public void init() throws SerialException {
    LOG.info("Modem init (port:{} speed:{})", port, speed);

    if (port == null) {
      throw new IllegalArgumentException("Port cannot be null");
    }

    mode = Mode.COMMAND;

    try {
      pipe = Pipe.open();
      lines = new ArrayList<>();

      // Start the reading in a thread
      LOG.debug("Starting the read thread for port {} {}", port, speed);
      readThread = new ReadThread(unsolicitedPatterns, unsolicitedResponseCallback, solicitedResponseCallback);
      readThread.execute(id, pipe.source());

      serialPort = new SerialPort(port);
      final boolean opened = serialPort.openPort();
      LOG.debug("Is port {} opened? {}", port, opened);
      serialPort.setParams(speed, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, true, true);
      // serialPort.setFlowControlMode(FLOWCONTROL_RTSCTS_IN | FLOWCONTROL_RTSCTS_OUT);
      // serialPort.setFlowControlMode(FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT);
      serialPort.setFlowControlMode(flowControlMode);
      serialPort.purgePort(PURGE_TXCLEAR | PURGE_RXCLEAR);
      final int mask = SerialPort.MASK_TXEMPTY + SerialPort.MASK_RXCHAR + SerialPort.MASK_RXFLAG + SerialPort.MASK_CTS + SerialPort.MASK_DSR + SerialPort.MASK_BREAK + SerialPort.MASK_RLSD + SerialPort.MASK_ERR + SerialPort.MASK_RING;
      serialPort.setEventsMask(mask);
      //serialPort.setDTR(true);
      //serialPort.setRTS(true);
      serialPort.addEventListener(new JsscSerialPortReader(serialPort, pipe.sink()));

      outputPipe = Pipe.open();
      outputStream = Channels.newOutputStream(outputPipe.sink());

      final WriteTask writetask = new WriteTask(outputPipe, serialPort);
      final Thread writeTaskThread = new Thread(writetask);
      writeTaskThread.start();

    } catch (final SerialPortException e) {
      LOG.error("SerialPortException {} on {} in method {}", e.getExceptionType(), e.getPortName(), e.getMethodName());
      close();
      throw new SerialException(e);
    } catch (final IOException e) {
      LOG.error("I/O exception: {}", e.getMessage());
      close();
      throw new SerialException(e);
    }
  }

  public void panic() {
    try {
      LOG.info("Modem: CTS:{} DSR:{} RLSD:{} RING:{}", serialPort.isCTS(), serialPort.isDSR(), serialPort.isRLSD(), serialPort.isRING());
      LOG.info("Modem: BufferBytes count IN:{} OUT:{}", serialPort.getInputBufferBytesCount(), serialPort.getOutputBufferBytesCount());
      final byte[] bytes = serialPort.readBytes();
      LOG.info("Read bytes: {} ", bytes != null ? Util.onlyPrintable(bytes) : null);
    } catch (SerialPortException e) {
      LOG.error("Serial error", e);
    }
  }

  private void closePipe() throws InterruptedException {
    LOG.trace("Closing pipe");
    try {
      pipe.source().close();
    } catch (final IOException e) {
      LOG.info("Could not close pipe: {}", e.getMessage());
    }
    Thread.sleep(250);
    readThread.close();
//    LOG.trace("Closing pipe, done");
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public OutputStream getOutputStream() {
    return outputStream;
  }

//  public void write(final byte[] bytes) throws SerialException {
//    try {
//      outputStream.write(bytes);
////      if (!serialPort.writeBytes(bytes)) {
////        LOG.warn("Could not write the bytes! ({})", new String(bytes));
////      }
//    } catch (final IOException e) {
//      LOG.error("Could not write serial bytes", e);
//      throw new SerialException(e);
//    }
//  }

  public byte[] read() {
    try {
      final Pipe.SourceChannel sourceChannel = pipe.source();
      if (!sourceChannel.isOpen()) {
        LOG.info("sourceChannel is not open");
      }
      final ByteBuffer dst = ByteBuffer.allocate(1024);
      final int bytesRead = sourceChannel.read(dst);
      // LOG.debug("read {} bytes from source channel", bytesRead);
      final byte[] data = new byte[bytesRead];
      System.arraycopy(dst.array(), 0, data, 0, bytesRead);
      LOG.info("read {} bytes [{}]", data.length, Util.onlyPrintable(data));
      return data;
    } catch (final IOException e) {
      LOG.error("Could not read serial bytes", e);
    }
    return new byte[0];
  }

  public void close() {
    if (serialPort != null) {
      LOG.info("Closing serial port {} which is {}", serialPort.getPortName(),
          (serialPort.isOpened() ? "open" : "closed"));
      try {
        if (serialPort.isOpened()) {
          serialPort.removeEventListener();
          serialPort.closePort();
        }
      } catch (final SerialPortException e) {
        LOG.warn("Error closing port", e);
      }
    } else {
      LOG.debug("Not closing, because SerialPort is null");
    }
    LOG.debug("Close source pipe");
    try {
      closePipe();
    } catch (InterruptedException e) {
      LOG.info("Could not close pipe", e);
    }
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(final Mode mode) {
    this.mode = mode;
  }

  public boolean isDsr() {
    try {
      return serialPort.isDSR();
    } catch (SerialPortException e) {
      LOG.warn("Unable to get DSR", e);
      throw new RuntimeException(e);
    }
  }

  public boolean isCts() {
    try {
      return serialPort.isCTS();
    } catch (SerialPortException e) {
      LOG.warn("Unable to get CTS", e);
      throw new RuntimeException(e);
    }
  }

  public boolean isCd() {
    try {
      return serialPort.isRLSD();
    } catch (SerialPortException e) {
      LOG.warn("Unable to get RLSD", e);
      throw new RuntimeException(e);
    }
  }

  public boolean isRing() {
    try {
      return serialPort.isRING();
    } catch (SerialPortException e) {
      LOG.warn("Unable to get RING", e);
      throw new RuntimeException(e);
    }
  }

  public void setDtr(final boolean enabled) {
    try {
      if (!serialPort.setDTR(enabled)) {
        LOG.warn("Set DTR was not successfull");
      }
    } catch (SerialPortException e) {
      LOG.warn("Unable to set DTR", e);
      throw new RuntimeException(e);
    }
  }

  public boolean setRts(final boolean enabled) {
    try {
      return serialPort.setRTS(enabled);
    } catch (SerialPortException e) {
      LOG.warn("Unable to set RTS", e);
      throw new RuntimeException(e);
    }
  }

  public void sendBreak(final int duration) {
    try {
      if (!serialPort.sendBreak(duration)) {
        LOG.warn("send break was not successfull");
      }
    } catch (SerialPortException e) {
      LOG.warn("Unable to send break", e);
      throw new RuntimeException(e);
    }
  }

  private class WriteTask implements Runnable {
    private final Pipe pipe;
    private final SerialPort serialPort;

    //private ByteBuffer dst = ByteBuffer.wrap(new byte[256]);
    private Selector selector = null;

    /**
     * Create a new DownloadTask with an URL to download.
     */
    public WriteTask(final Pipe pipe, final SerialPort serialPort) {
      this.pipe = pipe;
      this.serialPort = serialPort;
    }

    @Override
    public void run() {
      try {
        final Pipe.SourceChannel sourceChannel = pipe.source();
        sourceChannel.configureBlocking(false);
        selector = SelectorProvider.provider().openSelector();
        final SelectionKey readKey = sourceChannel.register(selector, SelectionKey.OP_READ);
        while (true) {
          final int n = selector.select(1000);
          if (!sourceChannel.isOpen()) {
            LOG.debug("SourceChannel is not open, exiting...");
            break;
          }
          if (n > 0) {
            final Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
              final SelectionKey selectedKey = it.next();
              // LOG.debug("Key {} accp:{} read:{} writ:{} valid:{}", key2, key2.isAcceptable(), key2.isReadable(), key2.isWritable(), key2.isValid());
              final ByteBuffer dst = ByteBuffer.wrap(new byte[256]);
              it.remove();
              if (selectedKey.isReadable()) {
                final int bytesRead = sourceChannel.read(dst);
                dst.flip();
                final byte[] data = readBytes(dst);
                LOG.info("{} bytes read: {} ({})", bytesRead, Util.bytesToHexString(data), data.length);
                serialPort.writeBytes(data);
              }
            }
          }
        }
      } catch (final ClosedChannelException e) {
        LOG.debug("ClosedChannel exception");
      } catch (final IOException e) {
        LOG.error("I/O exception", e);
      } catch (final Exception e) {
        LOG.error("Exception", e);
      } finally {
        try {
          selector.close();
        } catch (final IOException e) {
          LOG.error("I/O exception on selector close: {} {}", e.getMessage());
        }
      }
    }
  }
}
