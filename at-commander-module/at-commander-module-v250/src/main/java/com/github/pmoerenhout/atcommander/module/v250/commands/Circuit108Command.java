package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class Circuit108Command extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CIRCUIT_108 = "&D";

  private Integer value;

  public Circuit108Command(final AtCommander atCommander) {
    super(COMMAND_CIRCUIT_108, atCommander);
  }

  public Circuit108Command(final AtCommander atCommander, final int value) {
    super(COMMAND_CIRCUIT_108, atCommander);
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CIRCUIT_108);
      if (value != null) {
        sb.append(String.valueOf(value));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
