package com.github.pmoerenhout.atcommander.module.v250.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
