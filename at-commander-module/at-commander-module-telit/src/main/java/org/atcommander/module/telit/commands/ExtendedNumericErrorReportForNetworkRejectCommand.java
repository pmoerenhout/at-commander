package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ExtendedNumericErrorReportForNetworkRejectCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CEERNET = "#CEERNET";

  public ExtendedNumericErrorReportForNetworkRejectCommand(final AtCommander atCommander) {
    super(COMMAND_CEERNET, atCommander);
  }

  public ExtendedNumericErrorReportForNetworkRejectResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ExtendedNumericErrorReportForNetworkRejectResponse(super.execute(AT + COMMAND_CEERNET));
    } finally {
      available.release();
    }
  }
}
