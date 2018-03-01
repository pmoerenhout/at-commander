package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.telit.enums.JammedDetectReportMode;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class JammedDetectReportCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_JAMMED_DETECT_REPORT = "#JDR";

  private JammedDetectReportMode mode;
  private Integer maximumNoisePowerLevel;
  private Integer disturbedChannelMinimumNumber;

  public JammedDetectReportCommand(final AtCommander atCommander, final JammedDetectReportMode mode) {
    super(COMMAND_JAMMED_DETECT_REPORT, atCommander);
    this.mode = mode;
  }

  public JammedDetectReportCommand(final AtCommander atCommander, final JammedDetectReportMode mode, final int maximumNoisePowerLevel) {
    this(atCommander, mode);
    this.maximumNoisePowerLevel = maximumNoisePowerLevel;
  }

  public JammedDetectReportCommand(final AtCommander atCommander, final JammedDetectReportMode mode, final int maximumNoisePowerLevel,
                                   final int disturbedChannelMinimumNumber) {
    this(atCommander, mode, maximumNoisePowerLevel);
    this.disturbedChannelMinimumNumber = disturbedChannelMinimumNumber;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_JAMMED_DETECT_REPORT);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode.value()));
      if (maximumNoisePowerLevel != null) {
        sb.append(COMMA);
        sb.append(maximumNoisePowerLevel);
        if (disturbedChannelMinimumNumber != null) {
          sb.append(COMMA);
          sb.append(disturbedChannelMinimumNumber);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
