package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketShutdownCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SOCKET_SHUTDOWN = "#SH";

  private int socketId;

  public SocketShutdownCommand(final AtCommander atCommander, final int socketId) {
    super(COMMAND_SOCKET_SHUTDOWN, atCommander);
    this.socketId = socketId;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SOCKET_SHUTDOWN);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
