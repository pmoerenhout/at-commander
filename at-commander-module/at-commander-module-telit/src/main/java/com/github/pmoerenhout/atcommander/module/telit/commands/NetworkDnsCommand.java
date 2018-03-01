package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
