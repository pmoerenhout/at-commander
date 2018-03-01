package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GprsCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPRS = "#GPRS";

  private boolean activate;

  public GprsCommand(final AtCommander atCommander) {
    super(COMMAND_GPRS, atCommander);
  }

  public GprsCommand(final AtCommander atCommander, final boolean activate) {
    super(COMMAND_GPRS, atCommander);
    this.activate = activate;
  }

  public GprsResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPRS);
      sb.append(EQUAL);
      sb.append(oneOrZero(activate));
      return new GprsResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public GprsStatusResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPRS);
      sb.append(QUERY);
      return new GprsStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
