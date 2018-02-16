package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.telit.enums.GpsResetType;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GpsResetCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPSRST = "$GPSR";

  private GpsResetType type;

  public GpsResetCommand(final AtCommander atCommander,
                         final GpsResetType type) {
    super(COMMAND_GPSRST, atCommander);
    this.type = type;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPSRST);
      sb.append(EQUAL);
      sb.append(String.valueOf(type.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
