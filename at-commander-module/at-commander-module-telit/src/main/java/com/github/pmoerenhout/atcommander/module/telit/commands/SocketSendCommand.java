package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.nio.charset.StandardCharsets;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketSendCommand extends BaseCommand implements Command<BaseResponse> {

  private static final Logger LOG = LoggerFactory.getLogger(SocketSendCommand.class);
  private static final String COMMAND_SSEND = "#SSEND";

  private int socketId;
  private byte[] data;

  public SocketSendCommand(final AtCommander atCommander, final int socketId, final byte[] data) {
    super(COMMAND_SSEND, atCommander);
    this.socketId = socketId;
    this.data = data;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SSEND);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      final AtResponse s = super.execute(sb.toString());
      LOG.debug("Sending hexadecimal data");
      super.writeBytes(Util.bytesToHexString(data).getBytes(StandardCharsets.US_ASCII));
      LOG.debug("Now sending Ctrl-Z");
      return new EmptyResponse(super.execute(CTRLZ));
    } finally {
      available.release();
    }
  }
}
