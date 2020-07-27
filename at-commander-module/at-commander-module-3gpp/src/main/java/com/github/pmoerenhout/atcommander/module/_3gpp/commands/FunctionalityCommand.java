package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class FunctionalityCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CFUN = "+CFUN";

  private int functionality;
  private Boolean reset;

  public FunctionalityCommand(final AtCommander atCommander) {
    super(COMMAND_CFUN, atCommander);
  }

  public FunctionalityCommand(final AtCommander atCommander, final int functionality) {
    super(COMMAND_CFUN, atCommander);
    this.functionality = functionality;
  }

  public FunctionalityCommand(final AtCommander atCommander, final int functionality, final boolean reset) {
    super(COMMAND_CFUN, atCommander);
    this.functionality = functionality;
    this.reset = reset;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CFUN);
      sb.append(EQUAL);
      sb.append(functionality);
      if (reset != null) {
        sb.append(COMMA);
        sb.append(oneOrZero(reset));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
