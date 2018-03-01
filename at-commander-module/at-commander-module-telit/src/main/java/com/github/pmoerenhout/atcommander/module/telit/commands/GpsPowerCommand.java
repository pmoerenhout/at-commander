package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GpsPowerCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPSP = "$GPSP";

  private boolean power;

  public GpsPowerCommand(final AtCommander atCommander, final boolean power) {
    super(COMMAND_GPSP, atCommander);
    this.power = power;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPSP);
      sb.append(EQUAL);
      sb.append(oneOrZero(power));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
