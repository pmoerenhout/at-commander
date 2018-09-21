package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class CommandEchoCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_COMMAND_ECHO = "E";

  private boolean enable;

  public CommandEchoCommand(final AtCommander atCommander, final boolean enable) {
    super(COMMAND_COMMAND_ECHO, atCommander);
    this.enable = enable;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_COMMAND_ECHO);
        sb.append(oneOrZero(enable));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
