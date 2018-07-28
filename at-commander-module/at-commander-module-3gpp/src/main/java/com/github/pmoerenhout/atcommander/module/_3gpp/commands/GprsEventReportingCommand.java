package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GprsEventReportingCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGEREP = "+CGEREP";

  private int mode;
  private Integer bfr;

  public GprsEventReportingCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CGEREP, atCommander);
    this.mode = mode;
  }

  public GprsEventReportingCommand(final AtCommander atCommander, final int mode, final int bfr) {
    this(atCommander, mode);
    this.bfr = bfr;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGEREP);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      if (bfr != null){
        sb.append(EQUAL);
        sb.append(Integer.toString(bfr));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public int getMode() {
    return mode;
  }

  public Integer getBfr() {
    return bfr;
  }
}
