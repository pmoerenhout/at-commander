package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ExtendedNumericErrorReportCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CEER = "#CEER";

  public ExtendedNumericErrorReportCommand(final AtCommander atCommander) {
    super(COMMAND_CEER, atCommander);
  }

  public ExtendedNumericErrorReportResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ExtendedNumericErrorReportResponse(super.execute(AT + COMMAND_CEER));
    } finally {
      available.release();
    }
  }
}
