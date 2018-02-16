package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
