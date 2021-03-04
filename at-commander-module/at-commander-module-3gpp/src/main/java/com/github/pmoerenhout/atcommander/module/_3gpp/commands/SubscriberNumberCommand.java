package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SubscriberNumberCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SUBSCRIBER_NUMBER = "+CNUM";

  public SubscriberNumberCommand(final AtCommander atCommander) {
    super(COMMAND_SUBSCRIBER_NUMBER, atCommander);
  }

  @Override
  public SubscriberNumberResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SUBSCRIBER_NUMBER);
      return new SubscriberNumberResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
