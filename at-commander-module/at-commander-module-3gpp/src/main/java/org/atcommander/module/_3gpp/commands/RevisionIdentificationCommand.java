package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class RevisionIdentificationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GMR = "+CGMR";

  public RevisionIdentificationCommand(final AtCommander atCommander) {
    super(COMMAND_GMR, atCommander);
  }

  public RevisionIdentificationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new RevisionIdentificationResponse(super.execute(AT + COMMAND_GMR));
    } finally {
      available.release();
    }
  }

}
