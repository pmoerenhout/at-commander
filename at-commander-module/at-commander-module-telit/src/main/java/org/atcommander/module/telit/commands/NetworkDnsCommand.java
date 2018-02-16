package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class NetworkDnsCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_NWDNS = "#NWDNS";

  private int[] cids;

  public NetworkDnsCommand(final AtCommander atCommander, final int[] cids) {
    super(COMMAND_NWDNS, atCommander);
    this.cids = cids;
  }

  public NetworkDnsResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NWDNS);
      sb.append(EQUAL);
      for (int i = 0; i < cids.length; i++) {
        sb.append(String.valueOf(cids[i]));
        if (i != (cids.length - 1)) {
          sb.append(COMMA);
        }
      }
      return new NetworkDnsResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
