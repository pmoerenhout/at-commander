package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketReceiveCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SRECV = AT + "#SRECV";

  private int sid;
  private int maximumBytes;
  private Boolean udpInfo;

  public SocketReceiveCommand(final AtCommander atCommander, final int sid, final int maximumBytes) {
    super(COMMAND_SRECV, atCommander);
    this.sid = sid;
    this.maximumBytes = maximumBytes;
  }

  public SocketReceiveCommand(final AtCommander atCommander, final int sid, final int maximumBytes,
                              final Boolean udpInfo) {
    super(COMMAND_SRECV, atCommander);
    this.sid = sid;
    this.maximumBytes = maximumBytes;
    this.udpInfo = udpInfo;
  }

  public SocketReceiveResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SRECV);
      sb.append(EQUAL);
      sb.append(String.valueOf(sid));
      sb.append(COMMA);
      sb.append(String.valueOf(maximumBytes));
      if (udpInfo != null) {
        sb.append(COMMA);
        sb.append(udpInfo == Boolean.TRUE ? "1" : "0");
      }
      return new SocketReceiveResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setUdpInfo(final Boolean udpInfo) {
    this.udpInfo = udpInfo;
  }
}
