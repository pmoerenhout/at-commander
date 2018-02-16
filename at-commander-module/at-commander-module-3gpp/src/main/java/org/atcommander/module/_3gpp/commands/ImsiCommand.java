package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
