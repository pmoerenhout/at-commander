package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SS = "#SS";

  private Integer socketId;

  public SocketStatusCommand(final AtCommander atCommander) {
    super(COMMAND_SS, atCommander);
  }

  public SocketStatusCommand(final AtCommander atCommander, final int socketId) {
    super(COMMAND_SS, atCommander);
    this.socketId = socketId;
  }

  public SocketStatusResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SS);
      if (socketId != null) {
        sb.append(EQUAL);
        sb.append(String.valueOf(socketId));
      }
      return new SocketStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
