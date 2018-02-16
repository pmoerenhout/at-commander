package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GprsNetworkRegistrationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGREG = "+CGREG";

  private int mode;

  public GprsNetworkRegistrationCommand(final AtCommander atCommander) {
    super(COMMAND_CGREG, atCommander);
  }

  public GprsNetworkRegistrationCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CGREG, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGREG);
      sb.append(EQUAL);
      sb.append(mode);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public EmptyResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGREG);
      sb.append(QUERY);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
