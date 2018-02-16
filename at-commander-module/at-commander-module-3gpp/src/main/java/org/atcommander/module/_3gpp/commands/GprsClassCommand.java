package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GprsClassCommand extends BaseCommand implements Command<BaseResponse> {

  public static final String UMTS = "A";
  public static final String GSM_GPRS = "B";
  public static final String CLASS_C_GPRS = "CG";
  public static final String CLASS_C_GSM = "CC";

  private static final String COMMAND_CGCLASS = "+CGCLASS";

  private String clazz;

  public GprsClassCommand(final AtCommander atCommander, final String clazz) {
    super(COMMAND_CGCLASS, atCommander);
    this.clazz = clazz;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGCLASS);
      sb.append(EQUAL);
      sb.append(clazz);

      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
