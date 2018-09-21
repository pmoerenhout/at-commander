package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SmsAtRunCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SMSATRUN = "#SMSATRUN";

  private int mode;

  public SmsAtRunCommand(final AtCommander atCommander) {
    super(COMMAND_SMSATRUN, atCommander);
  }

  public SmsAtRunCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_SMSATRUN, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSATRUN);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SmsAtRunResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSATRUN);
      sb.append(QUERY);
      return new SmsAtRunResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
