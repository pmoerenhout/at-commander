package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GpsPowerCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPSP = "$GPSP";

  private boolean power;

  public GpsPowerCommand(final AtCommander atCommander, final boolean power) {
    super(COMMAND_GPSP, atCommander);
    this.power = power;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPSP);
      sb.append(EQUAL);
      sb.append(oneOrZero(power));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
