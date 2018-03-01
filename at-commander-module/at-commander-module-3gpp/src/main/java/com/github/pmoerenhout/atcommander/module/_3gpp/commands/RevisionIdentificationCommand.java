package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
