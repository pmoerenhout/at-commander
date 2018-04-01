package com.github.pmoerenhout.atcommander.module.neul.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
