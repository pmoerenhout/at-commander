package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.atcommander.module.v250.enums.OperatorSelectionMode;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class OperatorSelectionCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_COPS = "+COPS";

  private OperatorSelectionMode mode;
  private String mccMnc;
  private AccessTechnology accessTechnology;

  public OperatorSelectionCommand(final AtCommander atCommander) {
    super(COMMAND_COPS, atCommander);
  }

  public OperatorSelectionCommand(final AtCommander atCommander, final OperatorSelectionMode mode) {
    super(COMMAND_COPS, atCommander);
    this.mode = mode;
  }

  public OperatorSelectionCommand(final AtCommander atCommander, final OperatorSelectionMode mode,
                                  final String mccMnc) {
    super(COMMAND_COPS, atCommander);
    this.mode = mode;
    this.mccMnc = mccMnc;
  }

  public OperatorSelectionCommand(final AtCommander atCommander,
                                  final OperatorSelectionMode mode, final String mccMnc,
                                  final AccessTechnology accessTechnology) {
    super(COMMAND_COPS, atCommander);
    this.mode = mode;
    this.mccMnc = mccMnc;
    this.accessTechnology = accessTechnology;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_COPS);
      sb.append(EQUAL);
      sb.append(Integer.toString(mode.value()));
      sb.append(COMMA);
      sb.append("2");
      if (mccMnc != null) {
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(mccMnc);
        sb.append(DOUBLE_QUOTE);
        if (accessTechnology != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(accessTechnology.value()));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public OperatorSelectionResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_COPS);
      sb.append(QUERY);
      return new OperatorSelectionResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public OperatorSelectionTestResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_COPS);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new OperatorSelectionTestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
