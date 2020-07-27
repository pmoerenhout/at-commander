package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SlowClockCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_SLOW_CLOCK = "+QSCLK";

  private boolean enabled;

  public SlowClockCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_SLOW_CLOCK, atCommander);
  }

  public SlowClockCommand(final AtCommander atCommander, final boolean enabled) {
    super(COMMAND_QUECTEL_SLOW_CLOCK, atCommander);
    this.enabled = enabled;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_SLOW_CLOCK);
      sb.append(EQUAL);
      sb.append(oneOrZero(enabled));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
