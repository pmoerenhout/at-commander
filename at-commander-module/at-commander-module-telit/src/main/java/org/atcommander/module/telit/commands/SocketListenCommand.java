package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.telit.enums.TransmissionProtocol;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketListenCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SOCKET_LISTEN = "#SKTL";

  private boolean mode;
  private TransmissionProtocol transmissionProtocol;
  private int listenPort;
  private int closureType;

  public SocketListenCommand(final AtCommander atCommander, final boolean mode,
                             final TransmissionProtocol transmissionProtocol,
                             final int listenPort, final int closureType) {
    super(COMMAND_SOCKET_LISTEN, atCommander);
    this.mode = mode;
    this.transmissionProtocol = transmissionProtocol;
    this.listenPort = listenPort;
    this.closureType = closureType;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SOCKET_LISTEN);
      sb.append(EQUAL);
      sb.append(oneOrZero(mode));
      sb.append(COMMA);
      sb.append(String.valueOf(transmissionProtocol.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(listenPort));
      sb.append(COMMA);
      sb.append(String.valueOf(closureType));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
