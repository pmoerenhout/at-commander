package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
