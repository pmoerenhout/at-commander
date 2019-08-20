package com.github.pmoerenhout.atcommander.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.InitException;
import com.github.pmoerenhout.atcommander.api.Mode;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.basic.commands.AttentionCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleCommand;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;

public class Basic {

  private static final Logger LOG = LoggerFactory.getLogger(Basic.class);

  public SerialInterface serial;
  protected AtCommander atCommander;

  public Basic(final SerialInterface serial) {
    this.serial = serial;
    this.atCommander = new AtCommander(serial);
    serial.setSolicitedResponseCallback(atCommander);
  }

  public void init() throws InitException, SerialException, TimeoutException, ResponseException {
    try {
      atCommander.init();
    } catch (final Exception e) {
      LOG.error("Could not initialize basic modem", e);
      throw new InitException(e);
    }
  }

  public Mode getMode() {
    return serial.getMode();
  }

  public void setMode(Mode mode) {
    serial.setMode(mode);
  }

  public void close() {
    if (atCommander != null) {
      atCommander.close();
    }
  }

  public Command<BaseResponse> getSimpleCommand(final String command) {
    return new SimpleCommand(atCommander, command);
  }

  public void getAttention() throws SerialException, TimeoutException, ResponseException {
    final AttentionCommand command = new AttentionCommand(atCommander);
    command.set();
  }

  public void getAttention(final long timeout) throws SerialException, TimeoutException, ResponseException {
    final AttentionCommand command = new AttentionCommand(atCommander);
    command.setTimeout(timeout);
    command.set();
  }

  public boolean isResponsive() throws SerialException {
    try {
      final AttentionCommand command = new AttentionCommand(atCommander);
      command.setTimeout(1000);
      command.set();
      return true;
    } catch (final TimeoutException e) {
      LOG.warn("The AT interface is not responsive: {}", e.getMessage());
    } catch (final ResponseException e) {
      LOG.error("Other error on AT interface: {}", e);
    }
    return false;
  }

  public void escape(final byte escapeCharacter, final long delay) throws SerialException {
    // time of S12 in 1/50 of a second;
    // final int delay = 50 * 20;
    LOG.debug("Escape sequence with delay {}ms", delay);
    final byte[] esc = new byte[]{ escapeCharacter };
    sleep(delay);
    for (int i = 0; i < 3; i++) {
      LOG.debug("Send escape [{}] {}", (i + 1), Util.onlyPrintable(esc));
      atCommander.write(esc);
    }
    sleep(delay);
  }

  protected void sleep(final long millis) {
    try {
      Thread.sleep(millis);
    } catch (final InterruptedException e) {
      LOG.debug("Sleep was interrupted: {}", e.getMessage());
      throw new RuntimeException("Sleep was interrupted");
    }
  }

}
