package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PowerDownCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_POWER_DOWN = "+QPOWD";

  // 0 = Immediately power down
  // 1 = Normal power down
  private boolean normal;

  public PowerDownCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_POWER_DOWN, atCommander);
  }

  public PowerDownCommand(final AtCommander atCommander, final boolean normal) {
    super(COMMAND_QUECTEL_POWER_DOWN, atCommander);
    this.normal = normal;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_POWER_DOWN);
      sb.append(EQUAL);
      sb.append(oneOrZero(normal));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
