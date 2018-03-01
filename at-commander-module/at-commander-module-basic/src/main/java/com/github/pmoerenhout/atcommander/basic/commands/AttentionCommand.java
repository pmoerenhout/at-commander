package com.github.pmoerenhout.atcommander.basic.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
