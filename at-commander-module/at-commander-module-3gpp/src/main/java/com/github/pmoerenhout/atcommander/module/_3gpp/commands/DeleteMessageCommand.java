package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class DeleteMessageCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_MGD = "+CMGD";

  private int index;
  private Integer flag;

  public DeleteMessageCommand(final AtCommander atCommander, final int index) {
    super(COMMAND_MGD, atCommander);
    this.index = index;
  }

  public DeleteMessageCommand(final AtCommander atCommander, final int index, final int flag) {
    super(COMMAND_MGD, atCommander);
    this.index = index;
    this.flag = flag;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MGD);
      sb.append(EQUAL);
      sb.append(String.valueOf(index));
      if (flag != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(flag));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
