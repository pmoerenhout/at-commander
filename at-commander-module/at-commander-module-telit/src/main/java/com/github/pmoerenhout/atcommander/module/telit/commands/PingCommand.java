package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PingCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_PING = "#PING";

  private String address;
  private Integer numberOfRequests;
  private Integer length;
  private Integer timeout;
  private Integer timeToLive;

  public PingCommand(final AtCommander atCommander, final String address) {
    super(COMMAND_PING, atCommander);
    this.address = address;
  }

  public PingCommand(final AtCommander atCommander, final String address, final Integer numberOfRequests,
                     final Integer length, final Integer timeout, final Integer timeToLive) {
    super(COMMAND_PING, atCommander);
    this.address = address;
    this.numberOfRequests = numberOfRequests;
    this.length = length;
    this.timeout = timeout;
    this.timeToLive = timeToLive;
  }

  public PingResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_PING);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(address);
      sb.append(DOUBLE_QUOTE);
      if (numberOfRequests != null) {
        sb.append(COMMA);
        sb.append(Integer.valueOf(numberOfRequests));
        if (length != null) {
          sb.append(COMMA);
          sb.append(Integer.valueOf(length));
          if (timeout != null) {
            sb.append(COMMA);
            sb.append(Integer.toString(timeout));
          }
          if (timeToLive != null) {
            sb.append(COMMA);
            sb.append(Integer.valueOf(timeToLive));
          }
        }
      }
      return new PingResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
