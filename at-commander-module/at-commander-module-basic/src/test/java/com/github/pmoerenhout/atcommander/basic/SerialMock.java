package com.github.pmoerenhout.atcommander.basic;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.SolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.api.State;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.common.Util;

public class SerialMock implements SerialInterface {

  private static final Logger LOG = LoggerFactory.getLogger(SerialMock.class);

  private SolicitedResponseCallback solicitedResponseCallback;

  private Map<String, String[]> responses = new HashMap<>();

  public void setMockResponses(final String command, final String[] response) {
    responses.put(command, response);
  }

  public String getId() {
    LOG.info("Mock getId");
    return "mock";
  }

  public void init() throws SerialException {
    LOG.info("Mock init");
  }

  public void write(final byte[] data) throws SerialException {
    LOG.info("Mock write: {}", Util.onlyPrintable(data));
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

  public byte[] read() {
    LOG.info("Mock read: {}");
    return "OK\r\n".getBytes();
  }

  public void close() {
    LOG.info("Mock close");
  }

  public State getState() {
    return State.COMMAND;
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


}
