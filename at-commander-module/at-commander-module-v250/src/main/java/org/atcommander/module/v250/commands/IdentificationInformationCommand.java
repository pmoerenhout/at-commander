package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
