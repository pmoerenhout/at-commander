package com.github.pmoerenhout.atcommander.module.v250.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GlobalObjectIdentificationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GOI = "+GOI";

  public GlobalObjectIdentificationCommand(final AtCommander atCommander) {
    super(COMMAND_GOI, atCommander);
  }

  public GlobalObjectIdentificationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new GlobalObjectIdentificationResponse(super.execute(AT + COMMAND_GOI));
    } finally {
      available.release();
    }
  }

}
