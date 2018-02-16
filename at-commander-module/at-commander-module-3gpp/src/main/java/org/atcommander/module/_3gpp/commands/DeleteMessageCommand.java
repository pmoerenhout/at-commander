package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class DeleteMessageCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_MGD = "+CMGD";

  private int index;
  private Integer flag;

  public DeleteMessageCommand(final AtCommander atCommander, final int index) {
    super(COMMAND_MGD, atCommander);
    this.index = index;
  }

  public DeleteMessageCommand(final AtCommander atCommander, final int index, final int flag) {
    super(COMMAND_MGD, atCommander);
    this.index = index;
    this.flag = flag;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MGD);
      sb.append(EQUAL);
      sb.append(String.valueOf(index));
      if (flag != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(flag));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
