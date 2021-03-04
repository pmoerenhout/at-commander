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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketSendUdpExtendedCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SSENDUDPEXT = "#SSENDUDPEXT";

  private int socketId;
  private int bytesToSend;
  private String remoteIpAddress;
  private int remotePort;
  private byte[] data;

  public SocketSendUdpExtendedCommand(final AtCommander atCommander, final int socketId, final int bytesToSend,
                                      final String remoteIpAddress, final int remotePort, final byte[] data) {
    super(COMMAND_SSENDUDPEXT, atCommander);
    this.socketId = socketId;
    this.bytesToSend = bytesToSend;
    this.remoteIpAddress = remoteIpAddress;
    this.remotePort = remotePort;
    this.data = data;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SSENDUDPEXT);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(bytesToSend));
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(String.valueOf(remoteIpAddress));
      sb.append(DOUBLE_QUOTE);
      sb.append(COMMA);
      sb.append(String.valueOf(remotePort));
      final AtResponse s1 = super.execute(sb.toString());
      for (final String line : s1.getInformationalText()) {
        log.debug("Received line {}", line);
      }
      log.debug("Sending binary data");
      final AtResponse s2 = super.execute(data);
      for (final String line : s2.getInformationalText()) {
        log.debug("Received line {}", line);
      }
      return new EmptyResponse(s2);
    } finally {
      available.release();
    }
  }
}
