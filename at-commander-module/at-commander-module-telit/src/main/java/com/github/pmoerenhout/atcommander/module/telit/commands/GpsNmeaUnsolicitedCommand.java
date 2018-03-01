package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class GpsNmeaUnsolicitedCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPSNUM = "$GPSNMUN";

  private boolean enable;
  private Boolean gga;
  private Boolean gll;
  private Boolean gsa;
  private Boolean gsv;
  private Boolean rmc;
  private Boolean vtg;

  public GpsNmeaUnsolicitedCommand(final AtCommander atCommander, final boolean enable) {
    super(COMMAND_GPSNUM, atCommander);
    this.enable = enable;
  }

  public GpsNmeaUnsolicitedCommand(final AtCommander atCommander, final boolean enable, final Boolean gga,
                                   final Boolean gll, final Boolean gsa, final Boolean gsv, final Boolean rmc,
                                   final Boolean vtg) {
    super(COMMAND_GPSNUM, atCommander);
    this.enable = enable;
    this.gga = gga;
    this.gll = gll;
    this.gsa = gsa;
    this.gsv = gsv;
    this.rmc = rmc;
    this.vtg = vtg;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPSNUM);
      sb.append(EQUAL);
      sb.append(oneOrZero(enable));
      if (gga != null) {
        sb.append(COMMA);
        sb.append(oneOrZero(gga));
        if (gll != null) {
          sb.append(COMMA);
          sb.append(oneOrZero(gll));
          if (gsa != null) {
            sb.append(COMMA);
            sb.append(oneOrZero(gsa));
            if (gsv != null) {
              sb.append(COMMA);
              sb.append(oneOrZero(gsv));
              if (rmc != null) {
                sb.append(COMMA);
                sb.append(oneOrZero(rmc));
                if (vtg != null) {
                  sb.append(COMMA);
                  sb.append(oneOrZero(vtg));
                }
              }
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setGga(final Boolean gga) {
    this.gga = gga;
  }

  public void setGll(final Boolean gll) {
    this.gll = gll;
  }

  public void setGsa(final Boolean gsa) {
    this.gsa = gsa;
  }

  public void setGsv(final Boolean gsv) {
    this.gsv = gsv;
  }

  public void setRmc(final Boolean rmc) {
    this.rmc = rmc;
  }

  public void setVtg(final Boolean vtg) {
    this.vtg = vtg;
  }
}
