package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class QueryNetworkInformationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_QUERY_NETWORK_INFORMATION = "+QNWINFO";

  public QueryNetworkInformationCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_QUERY_NETWORK_INFORMATION, atCommander);
  }

  public QueryNetworkInformationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_QUERY_NETWORK_INFORMATION);
      return new QueryNetworkInformationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
