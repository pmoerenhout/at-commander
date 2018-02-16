package org.atcommander.basic.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SimpleCommand extends BaseCommand implements Command<BaseResponse> {

  private final String command;

  public SimpleCommand(final AtCommander atCommander, final String command) {
    super(command, atCommander);
    this.command = command;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new EmptyResponse(super.execute(command));
    } finally { available.release();
    }
  }

  public String getCommand() {
    return command;
  }
}
