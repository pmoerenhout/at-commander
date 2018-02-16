package org.atcommander.module.neul.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class NeulConfigCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_NEUL_CONFIG = "+NCONFIG";

  private String setting;
  private String value;

  public NeulConfigCommand(final AtCommander atCommander) {
    super(COMMAND_NEUL_CONFIG, atCommander);
  }

  public NeulConfigCommand(final AtCommander atCommander, final String setting, final String value) {
    super(COMMAND_NEUL_CONFIG, atCommander);
    this.setting = setting;
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NEUL_CONFIG);
      sb.append(EQUAL);
      sb.append(setting);
      sb.append(COMMA);
      sb.append(value);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public NeulConfigResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NEUL_CONFIG);
      sb.append(QUERY);
      return new NeulConfigResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
