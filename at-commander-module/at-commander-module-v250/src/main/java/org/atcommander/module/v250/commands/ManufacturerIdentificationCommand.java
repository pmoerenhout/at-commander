package org.atcommander.module.v250.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ManufacturerIdentificationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GMI = "+CGMI";

  public ManufacturerIdentificationCommand(final AtCommander atCommander) {
    super(COMMAND_GMI, atCommander);
  }

  public ManufacturerIdentificationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ManufacturerIdentificationResponse(super.execute(AT + COMMAND_GMI));
    } finally {
      available.release();
    }
  }
}
