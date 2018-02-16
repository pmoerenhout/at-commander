package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.SimpleResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
