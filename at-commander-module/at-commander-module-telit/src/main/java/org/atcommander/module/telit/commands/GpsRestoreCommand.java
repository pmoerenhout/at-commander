package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GpsRestoreCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPSRST = "$GPSRST";

  public GpsRestoreCommand(final AtCommander atCommander) {
    super(COMMAND_GPSRST, atCommander);
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new EmptyResponse(super.execute(AT + COMMAND_GPSRST));
    } finally {
      available.release();
    }
  }
}
