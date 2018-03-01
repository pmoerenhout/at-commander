package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class FacilityLockCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CLCK = "+CLCK";

  private String facility;
  private int mode;
  private String password;
  private Integer clazz;

  public FacilityLockCommand(final AtCommander atCommander, final String facility, final int mode) {
    super(COMMAND_CLCK, atCommander);
    this.facility = facility;
    this.mode = mode;
  }

  public FacilityLockResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CLCK);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(facility);
      sb.append(DOUBLE_QUOTE);
      sb.append(COMMA);
      sb.append(mode);
      if (clazz != null) {
        sb.append(COMMA);
        sb.append(clazz);
      }
      return new FacilityLockResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
