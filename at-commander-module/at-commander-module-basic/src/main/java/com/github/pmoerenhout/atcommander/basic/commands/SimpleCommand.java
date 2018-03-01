package com.github.pmoerenhout.atcommander.basic.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
