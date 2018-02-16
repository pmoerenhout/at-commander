package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketTypeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_ST = "#ST";

  private Integer sid;

  public SocketTypeCommand(final AtCommander atCommander) {
    super(COMMAND_ST, atCommander);
  }

  public SocketTypeCommand(final AtCommander atCommander, final int sid) {
    super(COMMAND_ST, atCommander);
    this.sid = sid;
  }

  public SocketTypeResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_ST);
      if (sid != null) {
        sb.append(EQUAL);
        sb.append(String.valueOf(sid));
      }
      return new SocketTypeResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
