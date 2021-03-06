package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.telit.enums.ClosureType;
import com.github.pmoerenhout.atcommander.module.telit.enums.TransmissionProtocol;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.module.telit.enums.ConnectionMode;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class SocketDialCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SD = "#SD";

  private int socketId;
  private TransmissionProtocol transmissionProtocol;
  private int remotePort;
  private String ipAddress;
  private ClosureType closureType;
  private Integer localPort;
  private ConnectionMode connectionMode;

  public SocketDialCommand(final AtCommander atCommander, final int socketId,
                           final TransmissionProtocol transmissionProtocol,
                           final int remotePort, final String ipAddress) {
    super(COMMAND_SD, atCommander);
    this.socketId = socketId;
    this.transmissionProtocol = transmissionProtocol;
    this.remotePort = remotePort;
    this.ipAddress = ipAddress;
    this.closureType = null;
    this.localPort = null;
    this.connectionMode = null;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SD);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(transmissionProtocol.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(remotePort));
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(String.valueOf(ipAddress));
      sb.append(DOUBLE_QUOTE);
      if (closureType != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(closureType.value()));
        if (localPort != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(localPort));
          if (connectionMode != null) {
            sb.append(COMMA);
            sb.append(String.valueOf(connectionMode.value()));
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setClosureType(final ClosureType closureType) {
    this.closureType = closureType;
  }

  public void setLocalPort(final Integer localPort) {
    this.localPort = localPort;
  }

  public void setConnectionMode(final ConnectionMode connectionMode) {
    this.connectionMode = connectionMode;
  }
}
