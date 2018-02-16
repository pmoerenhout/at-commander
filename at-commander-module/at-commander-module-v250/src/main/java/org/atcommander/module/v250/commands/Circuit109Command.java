package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class Circuit109Command extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CIRCUIT_109 = "&C";

  private Integer value;

  public Circuit109Command(final AtCommander atCommander) {
    super(COMMAND_CIRCUIT_109, atCommander);
  }

  public Circuit109Command(final AtCommander atCommander, final int value) {
    super(COMMAND_CIRCUIT_109, atCommander);
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CIRCUIT_109);
      if (value != null) {
        sb.append(String.valueOf(value));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
