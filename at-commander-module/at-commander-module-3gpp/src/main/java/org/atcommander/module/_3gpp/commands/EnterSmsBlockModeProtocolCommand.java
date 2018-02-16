package org.atcommander.module._3gpp.commands;

import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.AtCommander;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;

public class EnterSmsBlockModeProtocolCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_ESP = "+CESP";

  public EnterSmsBlockModeProtocolCommand(final AtCommander atCommander) {
    super(COMMAND_ESP, atCommander);
  }


  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_ESP);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}