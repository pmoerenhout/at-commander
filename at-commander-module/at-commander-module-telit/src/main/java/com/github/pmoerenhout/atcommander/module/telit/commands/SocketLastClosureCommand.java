package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketLastClosureCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SLASTCLOSURE = "#SLASTCLOSURE";

  private Integer socketId;

  public SocketLastClosureCommand(final AtCommander atCommander) {
    super(COMMAND_SLASTCLOSURE, atCommander);
  }

  public SocketLastClosureCommand(final AtCommander atCommander, final int socketId) {
    super(COMMAND_SLASTCLOSURE, atCommander);
    this.socketId = socketId;
  }

  public SocketLastClosureResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SLASTCLOSURE);
      if (socketId != null) {
        sb.append(EQUAL);
        sb.append(String.valueOf(socketId));
      }
      return new SocketLastClosureResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
