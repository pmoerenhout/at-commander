package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SynchronizeNetworkTimeProtocolCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_NTP = "+QNTP";

  private int contextId;
  private String address;
  private Integer port;
  private Boolean autoSetTime;

  public SynchronizeNetworkTimeProtocolCommand(final AtCommander atCommander, final int contextId, final String address) {
    super(COMMAND_QUECTEL_NTP, atCommander);
    this.contextId = contextId;
    this.address = address;
  }

  public SynchronizeNetworkTimeProtocolCommand(final AtCommander atCommander, final int contextId, final String address, final int port) {
    super(COMMAND_QUECTEL_NTP, atCommander);
    this.contextId = contextId;
    this.address = address;
    this.port = port;
  }

  public SynchronizeNetworkTimeProtocolCommand(final AtCommander atCommander, final int contextId, final String address, final int port, final boolean autoSetTime) {
    super(COMMAND_QUECTEL_NTP, atCommander);
    this.contextId = contextId;
    this.address = address;
    this.port = port;
    this.autoSetTime = autoSetTime;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_NTP);
      sb.append(EQUAL);
      sb.append(contextId);
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(address);
      sb.append(DOUBLE_QUOTE);
      if (port != null) {
        sb.append(COMMA);
        sb.append(port);
        if (autoSetTime != null) {
          sb.append(COMMA);
          sb.append(oneOrZero(autoSetTime));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
