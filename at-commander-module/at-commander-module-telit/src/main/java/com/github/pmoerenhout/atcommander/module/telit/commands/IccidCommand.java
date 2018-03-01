package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class IccidCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CCID = "#CCID";

  public IccidCommand(final AtCommander atCommander) {
    super(COMMAND_CCID, atCommander);
  }

  public IccidResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new IccidResponse(super.execute(AT + COMMAND_CCID));
    } finally {
      available.release();
    }
  }
}
