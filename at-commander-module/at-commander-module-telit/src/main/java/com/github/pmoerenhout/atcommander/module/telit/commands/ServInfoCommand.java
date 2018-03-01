package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class ServInfoCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SERVINFO = "#SERVINFO";

  private AccessTechnology accessTechnology;

  public ServInfoCommand(final AtCommander atCommander) {
    super(COMMAND_SERVINFO, atCommander);
  }

  public ServInfoCommand(final AtCommander atCommander, final AccessTechnology accessTechnology) {
    super(COMMAND_SERVINFO, atCommander);
    this.accessTechnology = accessTechnology;
  }

  public ServInfoResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new ServInfoResponse(super.execute(AT + COMMAND_SERVINFO), accessTechnology);
    } finally {
      available.release();
    }
  }
}
