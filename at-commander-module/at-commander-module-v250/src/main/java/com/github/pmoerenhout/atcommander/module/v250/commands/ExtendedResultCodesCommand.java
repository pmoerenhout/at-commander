package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ExtendedResultCodesCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_EXTENDED_RESULT_CODES = "X";

  private Integer value;

  public ExtendedResultCodesCommand(final AtCommander atCommander) {
    super(COMMAND_EXTENDED_RESULT_CODES, atCommander);
  }

  public ExtendedResultCodesCommand(final AtCommander atCommander, final int value) {
    super(COMMAND_EXTENDED_RESULT_CODES, atCommander);
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_EXTENDED_RESULT_CODES);
      if (value != null) {
        sb.append(String.valueOf(value));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
