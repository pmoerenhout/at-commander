package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ServiceCentreAddressCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CSCA = "+CSCA";

  // <type> - the type of number
  // 129 - national numbering scheme
  // 145 - international numbering scheme (contains the character "+")

  private String number;
  private Integer type;

  public ServiceCentreAddressCommand(final AtCommander atCommander) {
    super(COMMAND_CSCA, atCommander);
  }

  public ServiceCentreAddressCommand(final AtCommander atCommander, final String number) {
    super(COMMAND_CSCA, atCommander);
    this.number = number;
  }

  public ServiceCentreAddressCommand(final AtCommander atCommander, final String number, final int type) {
    super(COMMAND_CSCA, atCommander);
    this.number = number;
    this.type = type;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CSCA);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(number);
      sb.append(DOUBLE_QUOTE);
      if (type != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(type));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public ServiceCentreAddressResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CSCA);
      sb.append(QUERY);
      return new ServiceCentreAddressResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
