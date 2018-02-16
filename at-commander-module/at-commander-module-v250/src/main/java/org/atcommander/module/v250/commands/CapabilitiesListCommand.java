package org.atcommander.module.v250.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
