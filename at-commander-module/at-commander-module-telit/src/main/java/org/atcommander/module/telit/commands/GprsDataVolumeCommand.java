package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.telit.enums.GprsDataVolumeMode;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class GprsDataVolumeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GDATAVOL = "#GDATAVOL";

  private GprsDataVolumeMode mode;

  public GprsDataVolumeCommand(final AtCommander atCommander, final GprsDataVolumeMode mode) {
    super(COMMAND_GDATAVOL, atCommander);
    this.mode = mode;
  }

  public GprsDataVolumeResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GDATAVOL);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode.value()));
      return new GprsDataVolumeResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
