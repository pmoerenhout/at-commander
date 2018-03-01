package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GprsActivateCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGACT = "+CGACT";

  private boolean attach;
  private int cid;

  public GprsActivateCommand(final AtCommander atCommander) {
    super(COMMAND_CGACT, atCommander);
  }

  public GprsActivateCommand(final AtCommander atCommander, final boolean attach, final int cid) {
    super(COMMAND_CGACT, atCommander);
    this.attach = attach;
    this.cid = cid;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGACT);
      sb.append(EQUAL);
      sb.append(oneOrZero(attach));
      sb.append(COMMA);
      sb.append(String.valueOf(cid));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public GprsActStatusResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGACT);
      sb.append(QUERY);
      return new GprsActStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SimpleResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGACT);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new SimpleResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
