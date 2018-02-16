package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketInformationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SI = "#SI";

  private Integer socketId;

  public SocketInformationCommand(final AtCommander atCommander) {
    super(COMMAND_SI, atCommander);
  }

  public SocketInformationCommand(final AtCommander atCommander, final int socketId) {
    super(COMMAND_SI, atCommander);
    this.socketId = socketId;
  }

  public SocketInformationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SI);
      if (socketId != null) {
        sb.append(EQUAL);
        sb.append(String.valueOf(socketId));
      }
      return new SocketInformationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
