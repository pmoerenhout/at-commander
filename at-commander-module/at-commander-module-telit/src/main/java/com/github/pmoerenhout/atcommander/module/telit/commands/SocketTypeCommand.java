package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketTypeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_ST = "#ST";

  private Integer sid;

  public SocketTypeCommand(final AtCommander atCommander) {
    super(COMMAND_ST, atCommander);
  }

  public SocketTypeCommand(final AtCommander atCommander, final int sid) {
    super(COMMAND_ST, atCommander);
    this.sid = sid;
  }

  public SocketTypeResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_ST);
      if (sid != null) {
        sb.append(EQUAL);
        sb.append(String.valueOf(sid));
      }
      return new SocketTypeResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
