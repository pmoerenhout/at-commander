package org.atcommander.basic;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.InitException;
import org.atcommander.api.SerialException;
import org.atcommander.api.SerialInterface;
import org.atcommander.basic.commands.AttentionCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.SimpleCommand;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Basic {

  private static final Logger LOG = LoggerFactory.getLogger(Basic.class);

  //private static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>();
  public SerialInterface serial;
  protected AtCommander atCommander;

  public Basic(final SerialInterface serial) {
    this.serial = serial;
    this.atCommander = new AtCommander(serial);
    serial.setSolicitedResponseCallback(atCommander);
    // UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
  }

  public void init() throws InitException, SerialException, TimeoutException, ResponseException {
    try {
//      atCommander = new AtCommander(serial);
//      serial.setSolicitedResponseCallback(atCommander);
      atCommander.init();
    } catch (final Exception e) {
      LOG.error("Could not initialize basic modem", e);
      throw new InitException(e);
    }
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
    AttentionCommand command = new AttentionCommand(atCommander);
    command.set();
  }

  public boolean isResponsive() throws SerialException {
    try {
      final SimpleCommand command = new SimpleCommand(atCommander, "AT");
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
