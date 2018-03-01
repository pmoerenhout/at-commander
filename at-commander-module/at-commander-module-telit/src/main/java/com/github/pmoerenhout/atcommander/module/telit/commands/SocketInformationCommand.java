package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
