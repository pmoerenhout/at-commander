package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GprsAttachCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGATT = "+CGATT";

  private boolean attach;

  public GprsAttachCommand(final AtCommander atCommander) {
    super(COMMAND_CGATT, atCommander);
  }

  public GprsAttachCommand(final AtCommander atCommander, final boolean attach) {
    super(COMMAND_CGATT, atCommander);
    this.attach = attach;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGATT);
      sb.append(EQUAL);
      sb.append(oneOrZero(attach));

      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public GprsAttachResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGATT);
      sb.append(QUERY);
      return new GprsAttachResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
