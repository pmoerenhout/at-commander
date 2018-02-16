package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class MobileEquipmentEventReportingCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_MER = "+CMER";

  private int mode;
  private Integer keypadEventReporting;
  private Integer displayEventReporting;
  private Integer indicatorEventReporting;
  private Integer bufferClearing;

  public MobileEquipmentEventReportingCommand(final AtCommander atCommander) {
    super(COMMAND_MER, atCommander);
  }

  public MobileEquipmentEventReportingCommand(final AtCommander atCommander, final int mode, final Integer keypadEventReporting,
                                              final Integer displayEventReporting, final Integer indicatorEventReporting, final Integer bufferClearing) {
    super(COMMAND_MER, atCommander);
    this.mode = mode;
    this.keypadEventReporting = keypadEventReporting;
    this.displayEventReporting = displayEventReporting;
    this.indicatorEventReporting = indicatorEventReporting;
    this.bufferClearing = bufferClearing;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MER);
      sb.append(EQUAL);
      sb.append(mode);
      if (keypadEventReporting != null) {
        sb.append(COMMA);
        sb.append(keypadEventReporting);
        if (displayEventReporting != null) {
          sb.append(COMMA);
          sb.append(displayEventReporting);
          if (indicatorEventReporting != null) {
            sb.append(COMMA);
            sb.append(indicatorEventReporting);
            if (bufferClearing != null) {
              sb.append(COMMA);
              sb.append(bufferClearing);
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public EmptyResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MER);
      sb.append(QUERY);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
