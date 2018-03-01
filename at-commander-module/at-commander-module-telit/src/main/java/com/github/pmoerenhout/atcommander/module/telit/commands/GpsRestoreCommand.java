package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
