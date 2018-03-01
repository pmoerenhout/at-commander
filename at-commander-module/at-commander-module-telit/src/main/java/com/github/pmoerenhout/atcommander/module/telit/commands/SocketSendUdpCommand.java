package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketSendUdpCommand extends BaseCommand implements Command<BaseResponse> {

  private static final Logger LOG = LoggerFactory.getLogger(SocketSendUdpCommand.class);
  private static final String COMMAND_SSENDUDP = "#SSENDUDP";

  private int socketId;
  private String remoteIpAddress;
  private int remotePort;
  private byte[] data;

  public SocketSendUdpCommand(final AtCommander atCommander, final int socketId, final String remoteIpAddress,
                              final int remotePort, final byte[] data) {
    super(COMMAND_SSENDUDP, atCommander);
    this.socketId = socketId;
    this.remoteIpAddress = remoteIpAddress;
    this.remotePort = remotePort;
    this.data = data;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SSENDUDP);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(String.valueOf(remoteIpAddress));
      sb.append(DOUBLE_QUOTE);
      sb.append(COMMA);
      sb.append(String.valueOf(remotePort));
      final AtResponse s = super.execute(sb.toString());
      super.writeBytes(data);
      LOG.debug("Now sending Ctrl-Z");
      return new EmptyResponse(super.execute(CTRLZ, 60000));
    } finally {
      available.release();
    }
  }
}
