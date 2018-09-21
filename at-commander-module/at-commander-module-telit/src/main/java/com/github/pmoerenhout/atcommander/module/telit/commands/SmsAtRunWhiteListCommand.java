package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SmsAtRunWhiteListCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SMSATWL = "#SMSATWL";

  private int action;
  private int index;
  private Integer entryType;
  private String phonenumberOrPassword;

  public SmsAtRunWhiteListCommand(final AtCommander atCommander) {
    super(COMMAND_SMSATWL, atCommander);
  }

  public SmsAtRunWhiteListCommand(final AtCommander atCommander, final int action, final int index) {
    super(COMMAND_SMSATWL, atCommander);
    this.action = action;
    this.index = index;
  }

  public SmsAtRunWhiteListCommand(final AtCommander atCommander, final int action, final int index, final int entryType, final String phonenumberOrPassword) {
    super(COMMAND_SMSATWL, atCommander);
    this.action = action;
    this.index = index;
    this.entryType = entryType;
    this.phonenumberOrPassword = phonenumberOrPassword;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSATWL);
      sb.append(EQUAL);
      sb.append(action);
      sb.append(COMMA);
      sb.append(index);
      if (entryType != null){
        sb.append(COMMA);
        sb.append(entryType);
        if (phonenumberOrPassword != null){
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(phonenumberOrPassword);
          sb.append(DOUBLE_QUOTE);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
