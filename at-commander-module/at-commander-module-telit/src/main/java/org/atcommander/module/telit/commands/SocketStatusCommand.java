package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
