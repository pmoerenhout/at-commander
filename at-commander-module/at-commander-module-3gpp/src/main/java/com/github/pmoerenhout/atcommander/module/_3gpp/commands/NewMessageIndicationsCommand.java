package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class NewMessageIndicationsCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CNMI = "+CNMI";

  private int mode;
  private Integer mt;
  private Integer bm;
  private Integer ds;
  private Integer bfr;

  public NewMessageIndicationsCommand(final AtCommander atCommander) {
    super(COMMAND_CNMI, atCommander);
  }

  public NewMessageIndicationsCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CNMI, atCommander);
    this.mode = mode;
  }

  public NewMessageIndicationsCommand(final AtCommander atCommander, final int mode, final int mt) {
    super(COMMAND_CNMI, atCommander);
    this.mode = mode;
    this.mt = mt;
  }

  public NewMessageIndicationsCommand(final AtCommander atCommander, final int mode, final int mt, final int bm) {
    super(COMMAND_CNMI, atCommander);
    this.mode = mode;
    this.mt = mt;
    this.bm = bm;
  }

  public NewMessageIndicationsCommand(final AtCommander atCommander, final int mode, final int mt, final int bm, final int ds) {
    super(COMMAND_CNMI, atCommander);
    this.mode = mode;
    this.mt = mt;
    this.bm = bm;
    this.ds = ds;
  }

  public NewMessageIndicationsCommand(final AtCommander atCommander, final int mode, final int mt, final int bm, final int ds, final int bfr) {
    super(COMMAND_CNMI, atCommander);
    this.mode = mode;
    this.mt = mt;
    this.bm = bm;
    this.ds = ds;
    this.bfr = bfr;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CNMI);
      sb.append(EQUAL);
      sb.append(mode);
      if (mt != null) {
        sb.append(COMMA);
        sb.append(mt);
        if (bm != null) {
          sb.append(COMMA);
          sb.append(bm);
          if (ds != null) {
            sb.append(COMMA);
            sb.append(ds);
            if (bfr != null) {
              sb.append(COMMA);
              sb.append(bfr);
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public NewMessageIndicationsResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CNMI);
      sb.append(QUERY);
      return new NewMessageIndicationsResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}