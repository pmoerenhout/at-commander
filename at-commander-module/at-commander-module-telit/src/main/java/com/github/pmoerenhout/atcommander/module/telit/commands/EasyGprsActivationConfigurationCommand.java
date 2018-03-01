package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class EasyGprsActivationConfigurationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SGACTCFG = "#SGACTCFG";

  private int cid;
  private int retry;
  private Integer delay;
  private Integer urcMode;

  public EasyGprsActivationConfigurationCommand(final AtCommander atCommander, final int cid, final int retry) {
    super(COMMAND_SGACTCFG, atCommander);
    this.cid = cid;
    this.retry = retry;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SGACTCFG);
      sb.append(EQUAL);
      sb.append(String.valueOf(cid));
      sb.append(COMMA);
      sb.append(String.valueOf(retry));
      if (delay != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(delay));
        if (urcMode != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(urcMode));
        }

      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setDelay(final Integer delay) {
    this.delay = delay;
  }

  public void setUrcMode(final Integer urcMode) {
    this.urcMode = urcMode;
  }
}
