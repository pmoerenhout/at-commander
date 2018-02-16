package org.atcommander.module.v250.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
