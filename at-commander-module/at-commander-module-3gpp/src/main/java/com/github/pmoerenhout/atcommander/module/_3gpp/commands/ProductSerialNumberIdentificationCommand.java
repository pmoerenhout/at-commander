package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
