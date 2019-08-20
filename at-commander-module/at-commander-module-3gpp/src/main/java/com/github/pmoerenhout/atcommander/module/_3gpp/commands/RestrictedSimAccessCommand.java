package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;

public class RestrictedSimAccessCommand extends BaseCommand implements Command<BaseResponse> {

  static public final int COMMAND_READ_BINARY = 176;
  static public final int COMMAND_READ_RECORD = 178;
  static public final int COMMAND_GET_RESPONSE = 192;
  static public final int COMMAND_UPDATE_BINARY = 214;
  static public final int COMMAND_UPDATE_RECORD = 220;
  static public final int COMMAND_STATUS = 242;

  static public final int FILE_FPLMN = 0x6f7b;
  static public final int FILE_LOCATION = 0x6f7e;
  static public final int FILE_APN = 36614;

  private static final String COMMAND_CRSM = "+CRSM";

  private int command;
  private Integer fileId;
  private Integer p1;
  private Integer p2;
  private Integer p3;
  private byte[] data;

  public RestrictedSimAccessCommand(final AtCommander atCommander) {
    super(COMMAND_CRSM, atCommander);
  }

  public RestrictedSimAccessCommand(final AtCommander atCommander, int command) {
    super(COMMAND_CRSM, atCommander);
    this.command = command;
  }

  public RestrictedSimAccessCommand(final AtCommander atCommander, int command, int fileId) {
    super(COMMAND_CRSM, atCommander);
    this.command = command;
    this.fileId = fileId;
  }

  public RestrictedSimAccessCommand(final AtCommander atCommander, int command, int fileId, int p1, int p2, int p3) {
    super(COMMAND_CRSM, atCommander);
    this.command = command;
    this.fileId = fileId;
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  public RestrictedSimAccessCommand(final AtCommander atCommander, int command, int fileId, int p1, int p2, int p3,
                                    byte[] data) {
    super(COMMAND_CRSM, atCommander);
    this.command = command;
    this.fileId = fileId;
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.data = data;
  }

  public RestrictedSimAccessResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CRSM);
      sb.append(EQUAL);
      sb.append(String.valueOf(command));
      if (fileId != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(fileId));
        if (p1 != null && p2 != null && p3 != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(p1));
          sb.append(COMMA);
          sb.append(String.valueOf(p2));
          sb.append(COMMA);
          sb.append(String.valueOf(p3));
          if (data != null) {
            if (data.length == 0) {
              throw new IllegalArgumentException("The data to be written cannot be empty");
            }
            sb.append(COMMA);
            sb.append(Util.bytesToHexString(data).toUpperCase());
          }
        }
      }
      return new RestrictedSimAccessResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
