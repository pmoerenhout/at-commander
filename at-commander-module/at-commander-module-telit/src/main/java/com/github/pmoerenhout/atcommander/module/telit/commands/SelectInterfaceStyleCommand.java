package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SelectInterfaceStyleCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SELINT = "#SELINT";

  private int value;

  public SelectInterfaceStyleCommand(final AtCommander atCommander) {
    super(COMMAND_SELINT, atCommander);
  }

  public SelectInterfaceStyleCommand(final AtCommander atCommander, final int value) {
    super(COMMAND_SELINT, atCommander);
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SELINT);
      sb.append(EQUAL);
      sb.append(String.valueOf(value));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectInterfaceStyleResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SELINT);
      sb.append(QUERY);
      return new SelectInterfaceStyleResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
