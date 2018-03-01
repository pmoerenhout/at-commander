package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ServiceProviderNameCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SPN = "#SPN";

  public ServiceProviderNameCommand(final AtCommander atCommander) {
    super(COMMAND_SPN, atCommander);
  }

  public ServiceProviderNameResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ServiceProviderNameResponse(super.execute(AT + COMMAND_SPN));
    } finally {
      available.release();
    }
  }
}
