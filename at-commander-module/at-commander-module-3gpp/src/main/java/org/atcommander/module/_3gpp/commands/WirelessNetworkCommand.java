package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.commands.TestResponse;
import org.atcommander.module.v250.enums.WirelessNetwork;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class WirelessNetworkCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_WS46 = "+WS46";

  private WirelessNetwork wirelessNetwork;

  public WirelessNetworkCommand(final AtCommander atCommander) {
    super(COMMAND_WS46, atCommander);
  }

  public WirelessNetworkCommand(final AtCommander atCommander, final WirelessNetwork wirelessNetwork) {
    super(COMMAND_WS46, atCommander);
    this.wirelessNetwork = wirelessNetwork;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_WS46);
      sb.append(EQUAL);
      sb.append(String.valueOf(wirelessNetwork.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public WirelessNetworkStatusResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_WS46);
      sb.append(QUERY);
      return new WirelessNetworkStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public TestResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_WS46);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new TestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
