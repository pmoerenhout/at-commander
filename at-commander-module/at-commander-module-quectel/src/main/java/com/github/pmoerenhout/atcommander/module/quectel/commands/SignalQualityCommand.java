package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SignalQualityResponse;

public class SignalQualityCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_SIGNAL_QUALITY = "+QCSQ";

  public SignalQualityCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_SIGNAL_QUALITY, atCommander);
  }

  public SignalQualityResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new SignalQualityResponse(super.execute(AT + COMMAND_QUECTEL_SIGNAL_QUALITY));
    } finally {
      available.release();
    }
  }
}
