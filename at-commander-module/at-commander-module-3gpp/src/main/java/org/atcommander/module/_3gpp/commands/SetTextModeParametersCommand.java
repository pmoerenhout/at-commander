package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SetTextModeParametersCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SET_TEXT_MODE_PARAMETERS = "+CSMP";

  private int fo;
  private Integer vp;
  private Integer pid;
  private Integer dcs;

  public SetTextModeParametersCommand(final AtCommander atCommander, final int fo) {
    super(COMMAND_SET_TEXT_MODE_PARAMETERS, atCommander);
    this.fo = fo;
  }

  public SetTextModeParametersCommand(final AtCommander atCommander, final int fo, final int vp) {
    super(COMMAND_SET_TEXT_MODE_PARAMETERS, atCommander);
    this.fo = fo;
    this.vp = vp;
  }

  public SetTextModeParametersCommand(final AtCommander atCommander, final int fo, final int vp, final int pid) {
    super(COMMAND_SET_TEXT_MODE_PARAMETERS, atCommander);
    this.fo = fo;
    this.pid = pid;
  }

  public SetTextModeParametersCommand(final AtCommander atCommander, final int fo, final int vp, final int pid, final int dcs) {
    super(COMMAND_SET_TEXT_MODE_PARAMETERS, atCommander);
    this.fo = fo;
    this.pid = pid;
    this.dcs = dcs;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SET_TEXT_MODE_PARAMETERS);
      sb.append(EQUAL);
      sb.append(Integer.toString(fo));
      if (vp != null) {
        sb.append(COMMA);
        sb.append(Integer.toString(vp));
        if (pid != null) {
          sb.append(COMMA);
          sb.append(Integer.toString(pid));
        }
        if (dcs != null) {
          sb.append(COMMA);
          sb.append(Integer.toString(dcs));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}