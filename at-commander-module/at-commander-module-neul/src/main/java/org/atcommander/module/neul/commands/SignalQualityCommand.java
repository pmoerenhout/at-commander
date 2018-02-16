package org.atcommander.module.neul.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SignalQualityCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SIGNAL_QUALITY = "+CSQ";

  public SignalQualityCommand(final AtCommander atCommander) {
    super(COMMAND_SIGNAL_QUALITY, atCommander);
  }

  public SignalQualityResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new SignalQualityResponse(super.execute(AT + COMMAND_SIGNAL_QUALITY));
    } finally {
      available.release();
    }
  }
}
