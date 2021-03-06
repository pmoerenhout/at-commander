package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketConfigCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SOCKET_CONFIG = "#SCFG";

  private int socketId;
  private int cid;
  private int packetSize;
  private int maximumTimeout;
  private Integer connectionTimeout;
  private Integer txTimeout;

  public SocketConfigCommand(final AtCommander atCommander, final int socketId, final int cid,
                             final int packetSize, final int maximumTimeout, final int connectionTimeout,
                             final int txTimeout) {
    super(COMMAND_SOCKET_CONFIG, atCommander);
    this.socketId = socketId;
    this.cid = cid;
    this.packetSize = packetSize;
    this.maximumTimeout = maximumTimeout;
    this.connectionTimeout = connectionTimeout;
    this.txTimeout = txTimeout;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SOCKET_CONFIG);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(cid));
      sb.append(COMMA);
      sb.append(String.valueOf(packetSize));
      sb.append(COMMA);
      sb.append(String.valueOf(maximumTimeout));
      if (connectionTimeout != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(connectionTimeout));
        if (txTimeout != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(txTimeout));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
