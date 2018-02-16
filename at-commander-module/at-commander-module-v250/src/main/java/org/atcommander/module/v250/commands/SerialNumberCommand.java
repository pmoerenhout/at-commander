package org.atcommander.module.v250.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SerialNumberCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GSN = "+GSN";

  public SerialNumberCommand(final AtCommander atCommander) {
    super(COMMAND_GSN, atCommander);
  }

  public SerialNumberResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new SerialNumberResponse(super.execute(AT + COMMAND_GSN));
    } finally {
      available.release();
    }
  }
}
