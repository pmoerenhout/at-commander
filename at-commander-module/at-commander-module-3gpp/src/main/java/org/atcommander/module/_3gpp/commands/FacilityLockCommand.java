package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
