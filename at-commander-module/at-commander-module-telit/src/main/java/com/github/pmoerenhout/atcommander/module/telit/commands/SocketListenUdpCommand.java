package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class SocketListenUdpCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SOCKET_LISTEN_UDP = "#SLUDP";

  private int sid;
  private boolean listenState;
  private int listenPort;

  public SocketListenUdpCommand(final AtCommander atCommander, final int sid, final boolean listenState,
                                final int listenPort) {
    super(COMMAND_SOCKET_LISTEN_UDP, atCommander);
    this.sid = sid;
    this.listenState = listenState;
    this.listenPort = listenPort;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SOCKET_LISTEN_UDP);
      sb.append(EQUAL);
      sb.append(String.valueOf(sid));
      sb.append(COMMA);
      sb.append(oneOrZero(listenState));
      sb.append(COMMA);
      sb.append(String.valueOf(listenPort));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
