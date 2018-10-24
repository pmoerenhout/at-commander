package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GprsNetworkRegistrationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGREG = "+CGREG";

  private int mode;

  public GprsNetworkRegistrationCommand(final AtCommander atCommander) {
    super(COMMAND_CGREG, atCommander);
  }

  public GprsNetworkRegistrationCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CGREG, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGREG);
      sb.append(EQUAL);
      sb.append(mode);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public GprsNetworkRegistrationResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGREG);
      sb.append(QUERY);
      return new GprsNetworkRegistrationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
