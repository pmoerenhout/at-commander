package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
