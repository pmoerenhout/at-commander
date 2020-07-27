package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class TemperatureCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_TEMPERATURE = "+QTEMP";

  public TemperatureCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_TEMPERATURE, atCommander);
  }

  public TemperatureResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new TemperatureResponse(super.execute(AT + COMMAND_QUECTEL_TEMPERATURE));
    } finally {
      available.release();
    }
  }
}
