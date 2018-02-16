package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class NitzCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_NITZ = "#NITZ";

  private int value;
  private Integer mode;

  public NitzCommand(final AtCommander atCommander, final int value) {
    super(COMMAND_NITZ, atCommander);
    this.value = value;
  }

  public NitzCommand(final AtCommander atCommander, final int value, final int mode) {
    super(COMMAND_NITZ, atCommander);
    this.value = value;
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NITZ);
      sb.append(EQUAL);
      sb.append(String.valueOf(value));
      if (mode != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(mode));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public NitzResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NITZ);
      sb.append(QUERY);
      return new NitzResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
