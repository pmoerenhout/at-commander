package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

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
      sb.append(mode);
      if (auxMode != null) {
        sb.append(COMMA);
        sb.append(auxMode.intValue());
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
