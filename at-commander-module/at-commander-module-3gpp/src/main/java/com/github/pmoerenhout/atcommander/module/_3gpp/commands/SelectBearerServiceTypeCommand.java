package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.ConnectionElement;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataName;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataRate;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SelectBearerServiceTypeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CBST = "+CBST";

  private DataRate dataRate;
  private DataName dataName;
  private ConnectionElement connectionElement;

  public SelectBearerServiceTypeCommand(final AtCommander atCommander,
                                        final DataRate dataRate,
                                        final DataName dataName,
                                        final ConnectionElement connectionElement) {
    super(COMMAND_CBST, atCommander);
    this.dataRate = dataRate;
    this.dataName = dataName;
    this.connectionElement = connectionElement;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CBST);
      sb.append(EQUAL);
      sb.append(String.valueOf(dataRate.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(dataName.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(connectionElement.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
