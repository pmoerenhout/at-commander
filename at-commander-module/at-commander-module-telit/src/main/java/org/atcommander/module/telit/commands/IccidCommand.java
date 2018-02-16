package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class IccidCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CCID = "#CCID";

  public IccidCommand(final AtCommander atCommander) {
    super(COMMAND_CCID, atCommander);
  }

  public IccidResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new IccidResponse(super.execute(AT + COMMAND_CCID));
    } finally {
      available.release();
    }
  }
}
