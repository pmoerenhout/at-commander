package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.PdpType;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class DefinePdpContextCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CGDCONT = "+CGDCONT";

  private int cid;
  private PdpType pdpType;
  private String apn;
  private String pdpAddress;
  private Boolean dataCompression;
  private Boolean headerCompression;

  public DefinePdpContextCommand(final AtCommander atCommander, final int cid) {
    super(COMMAND_CGDCONT, atCommander);
    this.cid = cid;
  }

  public DefinePdpContextCommand(final AtCommander atCommander, final int cid,
                                 final PdpType pdpType, final String apn, final String pdpAddress,
                                 final Boolean dataCompression,
                                 final Boolean headerCompression) {
    super(COMMAND_CGDCONT, atCommander);
    this.cid = cid;
    this.pdpType = pdpType;
    this.apn = apn;
    this.pdpAddress = pdpAddress;
    this.dataCompression = dataCompression;
    this.headerCompression = headerCompression;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGDCONT);
      sb.append(EQUAL);
      sb.append(String.valueOf(cid));
      if (pdpType != null) {
        sb.append(COMMA);
        sb.append(pdpType);
        if (apn != null) {
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(apn);
          sb.append(DOUBLE_QUOTE);
          sb.append(COMMA);
          if (pdpAddress != null) {
            sb.append(DOUBLE_QUOTE);
            sb.append(pdpAddress);
            sb.append(DOUBLE_QUOTE);
          }
          if (dataCompression != null || headerCompression != null) {
            sb.append(COMMA);
            if (dataCompression != null) {
              sb.append(dataCompression == Boolean.TRUE ? "1" : "0");
            }
            if (headerCompression != null) {
              sb.append(COMMA);
              sb.append(headerCompression == Boolean.TRUE ? "1" : "0");
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setPdpType(final PdpType pdpType) {
    this.pdpType = pdpType;
  }

  public void setApn(final String apn) {
    this.apn = apn;
  }

  public void setPdpAddress(final String pdpAddress) {
    this.pdpAddress = pdpAddress;
  }

  public void setDataCompression(final Boolean dataCompression) {
    this.dataCompression = dataCompression;
  }

  public void setHeaderCompression(final Boolean headerCompression) {
    this.headerCompression = headerCompression;
  }
}
