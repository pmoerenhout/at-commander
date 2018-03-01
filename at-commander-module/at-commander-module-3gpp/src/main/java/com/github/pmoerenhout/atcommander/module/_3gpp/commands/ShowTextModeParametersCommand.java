package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class ShowTextModeParametersCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SHOW_TEXT_MODE = "+CSDH";

  private int show;

  public ShowTextModeParametersCommand(final AtCommander atCommander, final int show) {
    super(COMMAND_SHOW_TEXT_MODE, atCommander);
    this.show = show;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SHOW_TEXT_MODE);
      sb.append(EQUAL);
      sb.append(Integer.toString(show));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}