package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ImsiCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CIMI = "+CIMI";

  public ImsiCommand(final AtCommander atCommander) {
    super(COMMAND_CIMI, atCommander);
  }

  public ImsiResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ImsiResponse(super.execute(AT + COMMAND_CIMI));
    } finally {
      available.release();
    }
  }

}
