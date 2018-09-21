package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SmsAtRunConfigurationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SMSATRUNCFG = "#SMSATRUNCFG";

  private int instance;
  private Integer urcMode;
  private Integer timeout;

  public SmsAtRunConfigurationCommand(final AtCommander atCommander) {
    super(COMMAND_SMSATRUNCFG, atCommander);
  }

  public SmsAtRunConfigurationCommand(final AtCommander atCommander, final int instance) {
    super(COMMAND_SMSATRUNCFG, atCommander);
    this.instance = instance;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSATRUNCFG);
      sb.append(EQUAL);
      sb.append(instance);
      if (urcMode != null){
        sb.append(COMMA);
        sb.append(urcMode);
        if (timeout != null){
          sb.append(COMMA);
          sb.append(timeout);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SmsAtRunResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSATRUNCFG);
      sb.append(QUERY);
      return new SmsAtRunResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
