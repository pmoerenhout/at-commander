package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.telit.enums.GprsDataVolumeMode;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
