package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class VerboseCommand extends BaseCommand implements Command<BaseResponse> {

  // TA Response format
  private static final String COMMAND_VERBOSE = "V";

  private Integer format;

  public VerboseCommand(final AtCommander atCommander) {
    super(COMMAND_VERBOSE, atCommander);
  }

  public VerboseCommand(final AtCommander atCommander, final int format) {
    super(COMMAND_VERBOSE, atCommander);
    this.format = format;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_VERBOSE);
      if (format != null) {
        sb.append(String.valueOf(format));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
