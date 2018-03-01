package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ReadMessageCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_MESSAGE_READ = "+CMGR";
  private int index;

  public ReadMessageCommand(final AtCommander atCommander, final int index) {
    super(COMMAND_MESSAGE_READ, atCommander);
    this.index = index;
  }

  public ReadMessageResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MESSAGE_READ);
      sb.append(EQUAL);
      sb.append(Integer.toString(index));
      return new ReadMessageResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
