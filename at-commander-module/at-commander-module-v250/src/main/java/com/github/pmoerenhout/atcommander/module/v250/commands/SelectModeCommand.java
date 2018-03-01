package com.github.pmoerenhout.atcommander.module.v250.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SelectModeCommand extends BaseCommand implements Command<EmptyResponse> {

  private static final String COMMAND_FCLASS = "+FCLASS";

  private int mode;
  private boolean ituMode = false;

  public SelectModeCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_FCLASS, atCommander);
    this.mode = mode;
  }

  public SelectModeCommand(final AtCommander atCommander, final int mode, final boolean ituMode) {
    super(COMMAND_FCLASS, atCommander);
    this.mode = mode;
    this.ituMode = ituMode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_FCLASS);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      if (ituMode == Boolean.TRUE) {
        sb.append(".0");
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
