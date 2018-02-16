package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SelectTECharacterSetCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SCS = "+CSCS";

  private String characterSet;

  public SelectTECharacterSetCommand(final AtCommander atCommander) {
    super(COMMAND_SCS, atCommander);
  }

  public SelectTECharacterSetCommand(final AtCommander atCommander,
                                     final String characterSet
  ) {
    super(COMMAND_SCS, atCommander);
    this.characterSet = characterSet;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCS);
      sb.append(EQUAL);
      sb.append(characterSet);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectTECharacterSetResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCS);
      sb.append(QUERY);
      return new SelectTECharacterSetResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectTECharacterSetResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCS);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new SelectTECharacterSetResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
