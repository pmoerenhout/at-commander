package org.atcommander.module.v250.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class FactoryConfigurationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_FACTORY_CONFIGURATION = "&F";

  private Integer profile;

  public FactoryConfigurationCommand(final AtCommander atCommander) {
    super(COMMAND_FACTORY_CONFIGURATION, atCommander);
  }

  public FactoryConfigurationCommand(final AtCommander atCommander, final int profile) {
    super(COMMAND_FACTORY_CONFIGURATION, atCommander);
    this.profile = profile;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_FACTORY_CONFIGURATION);
      if (profile != null) {
        sb.append(String.valueOf(profile));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
