package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.v250.commands.AnyResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class HttpReceiveCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_HTTPRCV = "#HTTPRCV";

  private int profileId;
  private Integer maximumBytes;

  public HttpReceiveCommand(final AtCommander atCommander, final int profileId) {
    super(COMMAND_HTTPRCV, atCommander);
    this.profileId = profileId;
  }

  public HttpReceiveCommand(final AtCommander atCommander, final int profileId, final int maximumBytes) {
    super(COMMAND_HTTPRCV, atCommander);
    this.profileId = profileId;
    this.maximumBytes = maximumBytes;
  }

  public AnyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_HTTPRCV);
      sb.append(EQUAL);
      sb.append(String.valueOf(profileId));
      if (maximumBytes != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(maximumBytes));
      }
      return new AnyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
