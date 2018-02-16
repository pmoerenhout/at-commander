package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ReadNetworkStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_RFSTS = "#RFSTS";

  public ReadNetworkStatusCommand(final AtCommander atCommander) {
    super(COMMAND_RFSTS, atCommander);
  }

  public ReadNetworkStatusResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ReadNetworkStatusResponse(super.execute(AT + COMMAND_RFSTS));
    } finally {
      available.release();
    }
  }
}
