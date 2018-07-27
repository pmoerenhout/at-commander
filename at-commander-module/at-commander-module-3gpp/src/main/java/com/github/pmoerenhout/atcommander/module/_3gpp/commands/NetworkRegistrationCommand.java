package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class NetworkRegistrationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CREG = "+CREG";

  private int mode;

  public NetworkRegistrationCommand(final AtCommander atCommander) {
    super(COMMAND_CREG, atCommander);
  }

  public NetworkRegistrationCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CREG, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    BaseCommand.available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_CREG);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      BaseCommand.available.release();
    }
  }

  public NetworkRegistrationResponse read() throws SerialException, TimeoutException, ResponseException {
    BaseCommand.available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_CREG);
      sb.append(QUERY);
      return new NetworkRegistrationResponse(super.execute(sb.toString()));
    } finally {
      BaseCommand.available.release();
    }
  }

  public NetworkRegistrationResponse test() throws SerialException, TimeoutException, ResponseException {
    BaseCommand.available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_CREG);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new NetworkRegistrationResponse(super.execute(sb.toString()));
    } finally {
      BaseCommand.available.release();
    }
  }

}
