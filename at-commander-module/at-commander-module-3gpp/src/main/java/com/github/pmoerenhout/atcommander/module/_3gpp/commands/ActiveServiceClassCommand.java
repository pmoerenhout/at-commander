package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ActiveServiceClassCommand extends BaseCommand implements Command<BaseResponse> {

  public static final int DATA = 0;
  public static final int FAX_CLASS_1 = 1;
  public static final int VOICE = 8;

  private static final String COMMAND_ACTIVE_SERVICE_CLASS = "+FCLASS";

  private int serviceClass;

  public ActiveServiceClassCommand(final AtCommander atCommander, final int serviceClass) {
    super(COMMAND_ACTIVE_SERVICE_CLASS, atCommander);
    this.serviceClass = serviceClass;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_ACTIVE_SERVICE_CLASS);
      sb.append(EQUAL);
      sb.append(String.valueOf(serviceClass));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setServiceClass(final int serviceClass) {
    this.serviceClass = serviceClass;
  }
}
