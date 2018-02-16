package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
