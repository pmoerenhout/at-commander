package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class QuerySimStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUERY_SIM_STATUS = "#QSS";

  private int mode;

  public QuerySimStatusCommand(final AtCommander atCommander) {
    super(COMMAND_QUERY_SIM_STATUS, atCommander);
  }

  public QuerySimStatusCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_QUERY_SIM_STATUS, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUERY_SIM_STATUS);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public QuerySimStatusResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUERY_SIM_STATUS);
      sb.append(QUERY);
      return new QuerySimStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
