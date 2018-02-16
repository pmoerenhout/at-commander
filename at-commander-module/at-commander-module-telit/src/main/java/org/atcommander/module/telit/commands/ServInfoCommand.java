package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;

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
