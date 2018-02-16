package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SocketConfigurationExtended3Command extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SCFGEXT3 = "#SCFGEXT3";

  private int socketId;
  private int immediateResponse;
  private int closureTypeCmdModeEnabling;
  private Integer fastRing;
  private Integer unusedC;
  private Integer unusedD;

  public SocketConfigurationExtended3Command(final AtCommander atCommander, final int socketId,
                                             final int immediateResponse,
                                             final int closureTypeCmdModeEnabling) {
    super(COMMAND_SCFGEXT3, atCommander);
    this.socketId = socketId;
    this.immediateResponse = immediateResponse;
    this.closureTypeCmdModeEnabling = closureTypeCmdModeEnabling;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCFGEXT3);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(immediateResponse));
      sb.append(COMMA);
      sb.append(String.valueOf(closureTypeCmdModeEnabling));
      if (fastRing != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(fastRing));
        if (unusedC != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(unusedC));
          if (unusedD != null) {
            sb.append(COMMA);
            sb.append(String.valueOf(unusedD));
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setFastRing(final Integer fastRing) {
    this.fastRing = fastRing;
  }

  public void setUnusedC(final Integer unusedC) {
    this.unusedC = unusedC;
  }

  public void setUnusedD(final Integer unusedD) {
    this.unusedD = unusedD;
  }
}