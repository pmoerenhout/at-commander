package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PhoneActivityStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CPAS = "+CPAS";

  public PhoneActivityStatusCommand(final AtCommander atCommander) {
    super(COMMAND_CPAS, atCommander);
  }

  public PhoneActivityStatusResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new PhoneActivityStatusResponse(super.execute(AT + COMMAND_CPAS));
    } finally {
      available.release();
    }
  }

}
