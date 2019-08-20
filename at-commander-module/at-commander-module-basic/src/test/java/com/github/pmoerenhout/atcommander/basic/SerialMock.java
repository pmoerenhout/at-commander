package com.github.pmoerenhout.atcommander.basic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.Mode;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.SolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.common.Util;

public class SerialMock implements SerialInterface {

  private static final Logger LOG = LoggerFactory.getLogger(SerialMock.class);

  private InputStream inputStream;

  private PipedInputStream pipedInputStream;
  private PipedOutputStream outputStream;

  private Mode mode = Mode.COMMAND;

  private SolicitedResponseCallback solicitedResponseCallback;

  private Map<String, String[]> responses = new HashMap<>();

  public SerialMock() {
//    LOG.info("SerialMock init");
//    try {
//      pipedInputStream = new PipedInputStream();
//      outputStream = new PipedOutputStream(pipedInputStream);
//    } catch (IOException e) {
//      LOG.error("Could not initialize serial mock", e);
//    }LOG.info("SerialMock init, done");
  }

  public void setMockResponses(final String command, final String[] response) {
    responses.put(command, response);
  }

  public String getId() {
    LOG.info("Mock getId");
    return "mock";
  }

  public void init() throws SerialException {
    LOG.info("Mock init");
    try {
      pipedInputStream = new PipedInputStream();
      outputStream = new PipedOutputStream(pipedInputStream);

      ReaderThread readerThread = new ReaderThread(pipedInputStream);
      readerThread.start();

    } catch (IOException e) {
      LOG.error("Could not initialize serial mock", e);
    }
  }

  public void handleInput(final byte[] data) throws SerialException {
    LOG.info("Mock: {}", Util.onlyPrintable(data));
    final String command = StringUtils.chop(new String(data, StandardCharsets.US_ASCII));
    final String[] response = responses.get(command);
    if (response != null) {
      for (final String line : response) {
        LOG.info("Mock send solicited: {}", line);
        solicitedResponseCallback.solicited(line);
      }
      return;
    }
    throw new IllegalStateException("No response defined for command '" + command + "'");
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public OutputStream getOutputStream() {
    return outputStream;
  }

  public byte[] read() {
    LOG.info("Mock read(), return OK");
//    byte[] d = new byte[256];
//    pipedInputStream.read(d);
    return "OK\r\n".getBytes();
  }

  public void close() {
    LOG.info("Mock close");
  }

  public Mode getMode() {
    return this.mode;
  }

  public void setMode(final Mode mode) {
    this.mode = mode;
  }

  public boolean isDsr() {
    return false;
  }

  public boolean isCts() {
    return false;
  }

  public boolean isCd() {
    return false;
  }

  public void setDtr(final boolean state) {
    LOG.info("Mock setDtr: {}", state);
  }

  public void sendBreak(final int duration) {
    LOG.info("Mock sendBreak: {}", duration);
  }

  public void panic() {
  }

  public void setSolicitedResponseCallback(final SolicitedResponseCallback solicitedResponseCallback) {
    LOG.info("Mock setSolicitedResponseCallback: {}", solicitedResponseCallback);
    this.solicitedResponseCallback = solicitedResponseCallback;
  }

  public void addUnsolicited(final UnsolicitedPatternClass unsolicitedPatternClass) {
    LOG.info("Mock addUnsolicited: {}", unsolicitedPatternClass);
  }

  public class ReaderThread extends Thread {
    private final PipedInputStream pipedInputStream;

    public ReaderThread(final PipedInputStream pipedInputStream) {
      this.pipedInputStream = pipedInputStream;
    }

    public void run() {
      try {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[256];
        int read;
        while ((read = pipedInputStream.read(buffer, 0, buffer.length)) != -1) {
          LOG.trace("ReaderThread read {} byte!", read);
          baos.write(buffer, 0, read);
          int last = baos.toByteArray()[baos.size() - 1];
          if (last == 0x0d) {
            LOG.trace("received line: {}",
                Util.onlyPrintable(baos.toByteArray()));
            handleInput(baos.toByteArray());
            baos.reset();
          }
        }
        LOG.error("ReaderThread; closed");
        pipedInputStream.close();
      } catch (Exception e) {
        LOG.info("Error during reading of mock outputstream", e);
      }
    }
  }

}
