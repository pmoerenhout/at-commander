package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ModelIdentificationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GMM = "+CGMM";

  public ModelIdentificationCommand(final AtCommander atCommander) {
    super(COMMAND_GMM, atCommander);
  }

  public ModelIdentificationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ModelIdentificationResponse(super.execute(AT + COMMAND_GMM));
    } finally {
      available.release();
    }
  }

}
