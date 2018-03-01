package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import org.apache.commons.lang3.StringUtils;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PdPAddressCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CPGADDR = "+CGPADDR";

  private Integer[] pdpContext;

  public PdPAddressCommand(final AtCommander atCommander, Integer... integers) {
    super(COMMAND_CPGADDR, atCommander);
    pdpContext = integers;
  }

  public PdpAddressResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CPGADDR);
      sb.append(EQUAL);
      sb.append(StringUtils.join(pdpContext, COMMA));
      return new PdpAddressResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
