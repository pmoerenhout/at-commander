package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PingCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_PING = "+QPING";

  private int contextId;
  private String host;
  private Integer timeout;
  private Integer pingNum;

  public PingCommand(final AtCommander atCommander, final int contextId, final String host) {
    super(COMMAND_QUECTEL_PING, atCommander);
    this.contextId = contextId;
    this.host = host;
  }

  public PingCommand(final AtCommander atCommander, final int contextId, final String host, final int timeout) {
    super(COMMAND_QUECTEL_PING, atCommander);
    this.contextId = contextId;
    this.host = host;
    this.timeout = timeout;
  }

  public PingCommand(final AtCommander atCommander, final int contextId, final String host, final int timeout, final int pingNum) {
    super(COMMAND_QUECTEL_PING, atCommander);
    this.contextId = contextId;
    this.host = host;
    this.timeout = timeout;
    this.pingNum = pingNum;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_PING);
      sb.append(EQUAL);
      sb.append(contextId);
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(host);
      sb.append(DOUBLE_QUOTE);
      if (timeout != null) {
        sb.append(COMMA);
        sb.append(timeout);
        if (pingNum != null) {
          sb.append(COMMA);
          sb.append(pingNum);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
