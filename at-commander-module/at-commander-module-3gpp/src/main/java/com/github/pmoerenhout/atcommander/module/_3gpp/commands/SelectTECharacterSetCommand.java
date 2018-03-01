package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
