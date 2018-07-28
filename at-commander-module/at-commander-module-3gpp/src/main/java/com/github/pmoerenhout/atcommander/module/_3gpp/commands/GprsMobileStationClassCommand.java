package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.module.v250.commands.TestResponse;

public class GprsMobileStationClassCommand extends BaseCommand implements Command<BaseResponse> {

  public static final String CLASS_A = "A";
  public static final String CLASS_B = "B";
  public static final String CLASS_C = "C";
  public static final String CLASS_C_GPRS = "CG";
  public static final String CLASS_C_GSM = "CC";

  private static final String COMMAND_CGCLASS = "+CGCLASS";

  private String clazz;

  public GprsMobileStationClassCommand(final AtCommander atCommander) {
    super(COMMAND_CGCLASS, atCommander);
  }

  public GprsMobileStationClassCommand(final AtCommander atCommander, final String clazz) {
    super(COMMAND_CGCLASS, atCommander);
    this.clazz = clazz;
    if (isInvalidGprsMobileStationClass(clazz)) {
      throw new IllegalArgumentException("Invalid GPRS Mobile Station Class: " + clazz);
    }
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGCLASS);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(clazz);
      sb.append(DOUBLE_QUOTE);

      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public GprsMobileStationClassResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGCLASS);
      sb.append(QUERY);
      return new GprsMobileStationClassResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public TestResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGCLASS);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new TestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  private boolean isInvalidGprsMobileStationClass(final String clazz) {
    if (CLASS_A.equals(clazz) || CLASS_B.equals(clazz) || CLASS_C.equals(clazz) || CLASS_C_GPRS.equals(clazz) || CLASS_C_GSM.equals(clazz)) {
      return false;
    }
    return true;
  }
}
