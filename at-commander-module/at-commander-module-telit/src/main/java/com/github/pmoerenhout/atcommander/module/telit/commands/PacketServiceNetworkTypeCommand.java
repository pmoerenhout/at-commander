package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PacketServiceNetworkTypeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_PACKET_SERVICE_NETWORK_TYPE = "#PSNT";

  private int mode;

  public PacketServiceNetworkTypeCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_PACKET_SERVICE_NETWORK_TYPE, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_PACKET_SERVICE_NETWORK_TYPE);
      sb.append(EQUAL);
      sb.append(Integer.toString(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
