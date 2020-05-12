package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
      sb.append(mr);
      if (ackpdu != null) {
        sb.append(COMMA);
        sb.append(ackpdu);
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
