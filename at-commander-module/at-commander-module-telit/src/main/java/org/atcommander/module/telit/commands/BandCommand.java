package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.module.telit.enums.GsmBand;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.enums.UmtsBand;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

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
