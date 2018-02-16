package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SendCommandCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_CMGC = "+CMGC";

  private int mr;
  private Integer ackpdu;

  public SendCommandCommand(final AtCommander atCommander, final int mr) {
    super(COMMAND_CMGC, atCommander);
    this.mr = mr;
  }

  public SendCommandCommand(final AtCommander atCommander, final int mr, final int ackpdu) {
    super(COMMAND_CMGC, atCommander);
    this.mr = mr;
    this.ackpdu = ackpdu;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CMGC);
      sb.append(EQUAL);
      sb.append(String.valueOf(mr));
      if (ackpdu != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(ackpdu));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
