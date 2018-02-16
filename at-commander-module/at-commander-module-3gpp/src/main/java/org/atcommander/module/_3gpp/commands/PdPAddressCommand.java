package org.atcommander.module._3gpp.commands;

import org.apache.commons.lang3.StringUtils;
import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
