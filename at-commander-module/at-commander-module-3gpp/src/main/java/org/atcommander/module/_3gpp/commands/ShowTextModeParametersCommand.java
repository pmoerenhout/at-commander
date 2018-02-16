package org.atcommander.module._3gpp.commands;

import org.atcommander.Command;
import org.atcommander.AtCommander;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;

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