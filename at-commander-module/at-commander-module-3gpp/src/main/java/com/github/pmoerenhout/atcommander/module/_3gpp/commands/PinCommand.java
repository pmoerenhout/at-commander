package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PinCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CPIN = "+CPIN";

  private String pin;

  public PinCommand(final AtCommander atCommander) {
    super(COMMAND_CPIN, atCommander);
  }

  public PinCommand(final AtCommander atCommander, final String pin) {
    super(COMMAND_CPIN, atCommander);
    this.pin = pin;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CPIN);
      sb.append(EQUAL);
      sb.append(pin);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public PinResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CPIN);
      sb.append(QUERY);
      return new PinResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
