package com.github.pmoerenhout.atcommander.module.v250.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class CapabilitiesListCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CAP = "+CAP";

  public CapabilitiesListCommand(final AtCommander atCommander) {
    super(COMMAND_CAP, atCommander);
  }

  public CapabilitiesListResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new CapabilitiesListResponse(super.execute(AT + COMMAND_CAP));
    } finally {
      available.release();
    }
  }

}
