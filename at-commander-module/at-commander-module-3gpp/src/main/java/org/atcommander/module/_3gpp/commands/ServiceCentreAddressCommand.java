package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class ServiceCentreAddressCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CSCA = "+CSCA";

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

  public ServiceCentreAddressResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CSCA);
      sb.append(EQUAL);
      sb.append(number);
      if (type != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(type));
      }
      return new ServiceCentreAddressResponse(super.execute(sb.toString()));
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
