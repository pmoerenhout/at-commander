package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
