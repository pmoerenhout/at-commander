package org.atcommander.jssc;

import static jssc.SerialPort.PURGE_RXCLEAR;
import static jssc.SerialPort.PURGE_TXCLEAR;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.atcommander.api.ReadThread;
import org.atcommander.api.SerialException;
import org.atcommander.api.SerialInterface;
import org.atcommander.api.SolicitedResponseCallback;
import org.atcommander.api.State;
import org.atcommander.api.UnsolicitedPatternClass;
import org.atcommander.api.UnsolicitedResponseCallback;
import org.atcommander.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortException;

public class JsscSerial implements SerialInterface {

  private static final Logger LOG = LoggerFactory.getLogger(JsscSerial.class);

  private final List<UnsolicitedPatternClass> unsolicitedPatterns;
  private final UnsolicitedResponseCallback unsolicitedResponseCallback;
  State state;
  Pipe pipe;
  List<String> lines;
  private SerialPort serialPort;
  private String id;
  private final String port;
  private final int speed;
  private final int flowControlMode;
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
  }

  public void setSolicitedResponseCallback(final SolicitedResponseCallback solicitedResponseCallback) {
    this.solicitedResponseCallback = solicitedResponseCallback;
  }

  public void init() throws SerialException {
    LOG.info("Modem init (port:{} speed:{})", port, speed);

    if (port == null) {
      throw new IllegalArgumentException("Port cannot be null");
    }

    state = State.COMMAND;

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

  public void write(byte[] bytes) throws SerialException {
    try {
      if (!serialPort.writeBytes(bytes)) {
        LOG.warn("Could not write the bytes! ({})", new String(bytes));
      }
    } catch (final SerialPortException e) {
      LOG.error("Could not write serial bytes", e);
      throw new SerialException(e);
    }
  }

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
      LOG.debug("read {} bytes [{}]", data.length, Util.onlyPrintable(data));
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

  public State getState() {
    return state;
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

}
