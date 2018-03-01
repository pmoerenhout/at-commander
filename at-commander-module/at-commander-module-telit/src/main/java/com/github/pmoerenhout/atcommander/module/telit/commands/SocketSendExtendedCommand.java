package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketSendExtendedCommand extends BaseCommand implements Command<BaseResponse> {

  private static final Logger LOG = LoggerFactory.getLogger(SocketSendExtendedCommand.class);

  private static final String COMMAND_SSENDEXT = "#SSENDEXT";

  private int socketId;
  private int bytesToSend;
  private byte[] data;

  public SocketSendExtendedCommand(final AtCommander atCommander, final int socketId, final int bytesToSend,
                                   final byte[] data) {
    super(COMMAND_SSENDEXT, atCommander);
    this.socketId = socketId;
    this.bytesToSend = bytesToSend;
    this.data = data;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SSENDEXT);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(bytesToSend));
      // LOG.debug("Sending binary data");
      super.execute(sb.toString());
      return new EmptyResponse(super.execute(data));
    } finally {
      available.release();
    }
  }
}
