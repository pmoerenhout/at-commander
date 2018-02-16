package org.atcommander.module._3gpp.commands;

import org.atcommander.Command;
import org.atcommander.AtCommander;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;

public class SettingsDateFormatCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CSDF = "+CSDF";

  private int mode;
  private Integer auxMode;

  public SettingsDateFormatCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CSDF, atCommander);
    this.mode = mode;
  }

  public SettingsDateFormatCommand(final AtCommander atCommander, final int mode, final int auxMode) {
    super(COMMAND_CSDF, atCommander);
    this.mode = mode;
    this.auxMode = auxMode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CSDF);
      sb.append(EQUAL);
      sb.append(Integer.toString(mode));
      if (auxMode != null) {
        sb.append(COMMA);
        sb.append(Integer.toString(auxMode.intValue()));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
