package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class CellularResultCodesCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CELLULAR_RESULT_CODES = AT + "+CRC";

  private boolean extended;

  public CellularResultCodesCommand(final AtCommander atCommander) {
    super(COMMAND_CELLULAR_RESULT_CODES, atCommander);
  }

  public CellularResultCodesCommand(final AtCommander atCommander, final boolean extended) {
    super(COMMAND_CELLULAR_RESULT_CODES, atCommander);
    this.extended = extended;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CELLULAR_RESULT_CODES);
      sb.append(EQUAL);
      sb.append(oneOrZero(extended));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public CellularResultCodesResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CELLULAR_RESULT_CODES);
      sb.append(QUERY);
      return new CellularResultCodesResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public CellularResultCodesResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(COMMAND_CELLULAR_RESULT_CODES);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new CellularResultCodesResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
