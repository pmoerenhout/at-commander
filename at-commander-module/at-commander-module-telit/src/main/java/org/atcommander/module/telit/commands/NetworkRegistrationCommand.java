package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class NetworkRegistrationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_REG = "+CREG";

  private int mode;

  public NetworkRegistrationCommand(final AtCommander atCommander) {
    super(COMMAND_REG, atCommander);
  }

  public NetworkRegistrationCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_REG, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_REG);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public NetworkRegistrationResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_REG);
      sb.append(QUERY);
      return new NetworkRegistrationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
