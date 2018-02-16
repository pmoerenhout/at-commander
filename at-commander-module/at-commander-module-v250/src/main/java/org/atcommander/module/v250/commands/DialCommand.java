package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class DialCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_DIAL = "D";

  private String number;
  private Boolean voice;

  public DialCommand(final AtCommander atCommander, final String number) {
    super(COMMAND_DIAL, atCommander);
    this.number = number;
  }

  public DialResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_DIAL);
      sb.append(number);
      if (voice == Boolean.TRUE) {
        sb.append(";");
      }
      return new DialResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setNumber(final String number) {
    this.number = number;
  }

  public void setVoice(final Boolean voice) {
    this.voice = voice;
  }
}
