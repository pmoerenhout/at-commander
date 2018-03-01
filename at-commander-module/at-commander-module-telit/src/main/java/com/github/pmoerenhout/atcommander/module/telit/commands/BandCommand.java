package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.telit.enums.GsmBand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.UmtsBand;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class BandCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_BND = "#BND";

  private GsmBand band;
  private UmtsBand umtsBand;

  public BandCommand(final AtCommander atCommander) {
    super(COMMAND_BND, atCommander);
  }

  public BandCommand(final AtCommander atCommander, final GsmBand band) {
    super(COMMAND_BND, atCommander);
    this.band = band;
  }

  public BandCommand(final AtCommander atCommander, final GsmBand band, final UmtsBand umtsBand) {
    super(COMMAND_BND, atCommander);
    this.band = band;
    this.umtsBand = umtsBand;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_BND);
      sb.append(EQUAL);
      sb.append(String.valueOf(band.value()));
      if (umtsBand != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(umtsBand.value()));
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public BandResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_BND);
      sb.append(QUERY);
      return new BandResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
