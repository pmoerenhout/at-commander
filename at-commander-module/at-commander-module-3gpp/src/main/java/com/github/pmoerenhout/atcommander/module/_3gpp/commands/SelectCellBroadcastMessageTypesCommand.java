package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SelectCellBroadcastMessageTypesCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SELECT_CELLBROADCAST_MESSAGE_TYPES = "+CSCB";

  private int mode;
  private String mids;
  private String dcss;

  public SelectCellBroadcastMessageTypesCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_SELECT_CELLBROADCAST_MESSAGE_TYPES, atCommander);
    this.mode = mode;
  }

  public SelectCellBroadcastMessageTypesCommand(final AtCommander atCommander, final int mode, final String mids) {
    super(COMMAND_SELECT_CELLBROADCAST_MESSAGE_TYPES, atCommander);
    this.mode = mode;
    this.mids = mids;
  }

  public SelectCellBroadcastMessageTypesCommand(final AtCommander atCommander, final int mode, final String mids, final String dcss) {
    super(COMMAND_SELECT_CELLBROADCAST_MESSAGE_TYPES, atCommander);
    this.mode = mode;
    this.mids = mids;
    this.dcss = dcss;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SELECT_CELLBROADCAST_MESSAGE_TYPES);
      sb.append(EQUAL);
      sb.append(Integer.toString(mode));
      if (mids != null) {
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(mids);
        sb.append(DOUBLE_QUOTE);
        if (dcss != null) {
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(dcss);
          sb.append(DOUBLE_QUOTE);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}