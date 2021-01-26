package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class InitializationStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_INITIALIZATION_STATUS = "+QINISTAT";

  public InitializationStatusCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_INITIALIZATION_STATUS, atCommander);
  }

  public InitializationStatusResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_INITIALIZATION_STATUS);
      return new InitializationStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
