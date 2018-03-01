package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class IdentificationInformationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_I = "I";

  private Integer information;

  public IdentificationInformationCommand(final AtCommander atCommander) {
    super(COMMAND_I, atCommander);
  }

  public IdentificationInformationCommand(final AtCommander atCommander, final int information) {
    super(COMMAND_I, atCommander);
    this.information = information;
  }

  public AnyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_I);
      if (information != null) {
        sb.append(String.valueOf(information));
      }
      return new AnyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
