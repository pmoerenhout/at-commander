package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SParameterCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SREGISTER = "S";

  private int register;

  public SParameterCommand(final AtCommander atCommander, final int register) {
    super(COMMAND_SREGISTER, atCommander);
    this.register = register;
  }

  public SimpleResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SREGISTER);
      sb.append(register);
      sb.append(QUERY);
      return new SimpleResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
