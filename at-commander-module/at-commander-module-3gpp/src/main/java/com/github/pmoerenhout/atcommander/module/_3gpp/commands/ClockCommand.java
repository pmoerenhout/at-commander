package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ClockCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CLOCK = AT + "+CCLK";

  // "yy/MM/dd,hh:mm:ss±zz"
  private String time;

  public ClockCommand(final AtCommander atCommander) {
    super(COMMAND_CLOCK, atCommander);
  }

  public ClockCommand(final AtCommander atCommander, final String time) {
    super(COMMAND_CLOCK, atCommander);
    this.time = time;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CLOCK);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(time);
      sb.append(DOUBLE_QUOTE);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public ClockResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CLOCK);
      sb.append(QUERY);
      return new ClockResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public EmptyResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CLOCK);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
