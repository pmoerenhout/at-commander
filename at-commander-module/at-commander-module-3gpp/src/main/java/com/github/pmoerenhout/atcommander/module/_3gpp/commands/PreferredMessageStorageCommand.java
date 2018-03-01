package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class PreferredMessageStorageCommand extends BaseCommand implements Command<EmptyResponse> {

  static final String COMMAND_PMS = "+CPMS";

  private String mem1;
  private String mem2;
  private String mem3;

  public PreferredMessageStorageCommand(final AtCommander atCommander, final String mem1) {
    super(COMMAND_PMS, atCommander);
    this.mem1 = mem1;
  }

  public PreferredMessageStorageCommand(final AtCommander atCommander, final String mem1, final String mem2) {
    super(COMMAND_PMS, atCommander);
    this.mem1 = mem1;
    this.mem2 = mem2;
  }

  public PreferredMessageStorageCommand(final AtCommander atCommander, final String mem1, final String mem2, final String mem3) {
    super(COMMAND_PMS, atCommander);
    this.mem1 = mem1;
    this.mem2 = mem2;
    this.mem3 = mem3;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_PMS);
      sb.append(EQUAL);
      sb.append(DOUBLE_QUOTE);
      sb.append(mem1);
      sb.append(DOUBLE_QUOTE);
      if (mem2 != null) {
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(mem2);
        sb.append(DOUBLE_QUOTE);
        if (mem3 != null) {
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(mem3);
          sb.append(DOUBLE_QUOTE);
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public PreferredMessageStorageResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_PMS);
      sb.append(QUERY);
      return new PreferredMessageStorageResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
