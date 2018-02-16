package org.atcommander.module._3gpp.commands;

import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.AtCommander;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;

public class NetworkRegistrationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CREG = "+CREG";

  private int mode;

  public NetworkRegistrationCommand(final AtCommander atCommander) {
    super(COMMAND_CREG, atCommander);
  }

  public NetworkRegistrationCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CREG, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    BaseCommand.available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_CREG);
      sb.append(BaseCommand.EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      BaseCommand.available.release();
    }
  }

  public NetworkRegistrationResponse read() throws SerialException, TimeoutException, ResponseException {
    BaseCommand.available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_CREG);
      sb.append(BaseCommand.QUERY);
      return new NetworkRegistrationResponse(super.execute(sb.toString()));
    } finally {
      BaseCommand.available.release();
    }
  }

}
