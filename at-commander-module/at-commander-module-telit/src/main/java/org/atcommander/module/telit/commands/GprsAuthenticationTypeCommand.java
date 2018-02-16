package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.enums.Authentication;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GprsAuthenticationTypeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GAUTH = "#GAUTH";

  private Authentication type;

  public GprsAuthenticationTypeCommand(final AtCommander atCommander, final Authentication type) {
    super(COMMAND_GAUTH, atCommander);
    this.type = type;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GAUTH);
      sb.append(EQUAL);
      sb.append(String.valueOf(type.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  @Override
  public BandResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_GAUTH);
      sb.append(QUERY);
      return new BandResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
