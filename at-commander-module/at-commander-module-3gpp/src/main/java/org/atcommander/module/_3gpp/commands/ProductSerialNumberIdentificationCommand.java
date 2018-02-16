package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ProductSerialNumberIdentificationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GSN = "+CGSN";

  public ProductSerialNumberIdentificationCommand(final AtCommander atCommander) {
    super(COMMAND_GSN, atCommander);
  }

  public ProductSerialNumberIdentificationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ProductSerialNumberIdentificationResponse(super.execute(AT + COMMAND_GSN));
    } finally {
      available.release();
    }
  }

}
