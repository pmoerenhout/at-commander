package com.github.pmoerenhout.atcommander.module.neul.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
