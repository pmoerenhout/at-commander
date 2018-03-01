package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class EasyGprsActivationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SGACT = "#SGACT";

  private int cid;
  private boolean status;
  private String userId;
  private String password;

  public EasyGprsActivationCommand(final AtCommander atCommander) {
    super(COMMAND_SGACT, atCommander);
  }

  public EasyGprsActivationCommand(final AtCommander atCommander, final int cid, final boolean status) {
    super(COMMAND_SGACT, atCommander);
    this.cid = cid;
    this.status = status;
  }

  public EasyGprsActivationCommand(final AtCommander atCommander, final int cid, final boolean status, final String userId, final String password) {
    super(COMMAND_SGACT, atCommander);
    this.cid = cid;
    this.status = status;
    this.userId = userId;
    this.password = password;
  }

  public EasyGprsActivationResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SGACT);
      sb.append(EQUAL);
      sb.append(String.valueOf(cid));
      sb.append(COMMA);
      sb.append(oneOrZero(status));
      if (userId != null) {
        sb.append(COMMA);
        sb.append(userId);
        if (password != null) {
          sb.append(COMMA);
          sb.append(password);
        }
      }
      return new EasyGprsActivationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public EasyGprsActivationReadResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SGACT);
      sb.append(QUERY);
      return new EasyGprsActivationReadResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public void setPassword(final String password) {
    this.password = password;
  }
}
