package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.telit.enums.ConnectionMode;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketAcceptCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SA = "#SA";

  private int sid;
  private ConnectionMode connectionMode;

  public SocketAcceptCommand(final AtCommander atCommander, final int sid) {
    super(COMMAND_SA, atCommander);
    this.sid = sid;
  }

  public SocketAcceptCommand(final AtCommander atCommander, final int sid, final ConnectionMode connectionMode) {
    super(COMMAND_SA, atCommander);
    this.sid = sid;
    this.connectionMode = connectionMode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SA);
      sb.append(EQUAL);
      sb.append(String.valueOf(sid));
      if (connectionMode != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(connectionMode.value()));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
