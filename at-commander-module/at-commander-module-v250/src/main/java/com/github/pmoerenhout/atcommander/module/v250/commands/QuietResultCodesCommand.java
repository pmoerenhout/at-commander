package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class QuietResultCodesCommand extends BaseCommand implements Command<BaseResponse> {

  public static final int ENABLES_RESULT_CODES = 0;
  public static final int DISABLES_RESULT_CODES = 1;
  public static final int DISABLES_RESULT_CODES_BACKWARD_COMPATIBILITY = 2;

  // TA Response format
  private static final String COMMAND_QUIET = "Q";

  private Integer mode;

  public QuietResultCodesCommand(final AtCommander atCommander) {
    super(COMMAND_QUIET, atCommander);
  }

  public QuietResultCodesCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_QUIET, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUIET);
      if (mode != null) {
        sb.append(String.valueOf(mode));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
