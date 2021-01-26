package com.github.pmoerenhout.atcommander.module.quectel.commands;

import java.util.Arrays;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ExtendedConfigurationSettingsCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_EXTENDED_CONFIGURATION_SETTINGS = "+QCFG";

  private String parameter;
  private Object[] values;

  public ExtendedConfigurationSettingsCommand(final AtCommander atCommander, final String parameter) {
    super(COMMAND_QUECTEL_EXTENDED_CONFIGURATION_SETTINGS, atCommander);
    this.parameter = parameter;
  }

  public ExtendedConfigurationSettingsCommand(final AtCommander atCommander, final String parameter, final Object... values) {
    super(COMMAND_QUECTEL_EXTENDED_CONFIGURATION_SETTINGS, atCommander);
    this.parameter = parameter;
    this.values = values;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_EXTENDED_CONFIGURATION_SETTINGS);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(parameter);
      sb.append(DOUBLE_QUOTE);
      Arrays.stream(values).forEach(v -> {
        sb.append(COMMA);
        append(sb, v);
      });
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  private void append(final StringBuilder sb, final Object value) {
    if (value instanceof Boolean) {
      sb.append(oneOrZero(((Boolean) value).booleanValue()));
      return;
    }
    sb.append(value);
  }
}
