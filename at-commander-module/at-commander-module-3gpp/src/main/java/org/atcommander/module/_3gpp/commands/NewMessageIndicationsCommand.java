package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class NewMessageIndicationsCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CNMI = "+CNMI";

  private int mode;
  private Integer mt;
  private Integer bm;
  private Integer ds;
  private Integer bfr;

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
      sb.append(Integer.toString(mode));
      if (mt != null) {
        sb.append(COMMA);
        sb.append(Integer.toString(mt));
        if (bm != null) {
          sb.append(COMMA);
          sb.append(Integer.toString(bm));
          if (ds != null) {
            sb.append(COMMA);
            sb.append(Integer.toString(ds));
            if (bfr != null) {
              sb.append(COMMA);
              sb.append(Integer.toString(bfr));
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public MoreMessagesToSendResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CNMI);
      sb.append(QUERY);
      return new MoreMessagesToSendResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}