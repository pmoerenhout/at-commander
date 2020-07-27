package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ActivatePdpContextCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_ACTIVATE_PDP_CONTEXT = "+QIACT";

  private int contextId;

  public ActivatePdpContextCommand(final AtCommander atCommander, final int contextId) {
    super(COMMAND_QUECTEL_ACTIVATE_PDP_CONTEXT, atCommander);
    this.contextId = contextId;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_ACTIVATE_PDP_CONTEXT);
      sb.append(EQUAL);
      sb.append(contextId);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
