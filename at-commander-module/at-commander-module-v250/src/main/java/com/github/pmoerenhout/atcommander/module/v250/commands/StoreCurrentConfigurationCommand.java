package com.github.pmoerenhout.atcommander.module.v250.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class StoreCurrentConfigurationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_STORE_CURRENT_CONFIGURATION = "&W";

  private Integer profile;

  public StoreCurrentConfigurationCommand(final AtCommander atCommander) {
    super(COMMAND_STORE_CURRENT_CONFIGURATION, atCommander);
  }

  public StoreCurrentConfigurationCommand(final AtCommander atCommander, final int profile) {
    super(COMMAND_STORE_CURRENT_CONFIGURATION, atCommander);
    this.profile = profile;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_STORE_CURRENT_CONFIGURATION);
      if (profile != null) {
        sb.append(String.valueOf(profile));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
