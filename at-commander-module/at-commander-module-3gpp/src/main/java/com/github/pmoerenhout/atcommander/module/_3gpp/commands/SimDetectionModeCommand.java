package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SimDetectionModeCommand extends BaseCommand implements Command<BaseResponse> {

  public static final int SIMDET_IGNORE_PIN_SIMULATE_SIM_NOT_INSERTED = 0;
  public static final int SIMDET_IGNORE_PIN_SIMULATE_SIM_INSERTED = 1;
  public static final int SIMDET_AUTOMATIC_PIN = 2;

  private static final String COMMAND_SIMDET = "#SIMDET";

  // 0=ignoreNotInserted, 1=ignoreInserted, 2=automaticDetect
  private int mode;

  public SimDetectionModeCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_SIMDET, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SIMDET);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
