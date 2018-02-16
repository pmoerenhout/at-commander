package org.atcommander.basic.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class AttentionCommand extends BaseCommand implements Command<BaseResponse> {

  public AttentionCommand(final AtCommander atCommander) {
    super("", atCommander);
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      return new EmptyResponse(super.execute(AT));
    } finally { available.release();
    }
  }


}
