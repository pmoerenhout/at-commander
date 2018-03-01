package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
