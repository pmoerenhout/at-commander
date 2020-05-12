package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class UnstructuredSupplementaryServiceDataCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_CUSD = "+CUSD";

  private Integer type;
  private String ussdString;
  private Integer dcs;

  public UnstructuredSupplementaryServiceDataCommand(final AtCommander atCommander) {
    super(COMMAND_CUSD, atCommander);
  }

  public UnstructuredSupplementaryServiceDataCommand(final AtCommander atCommander, final Integer type) {
    super(COMMAND_CUSD, atCommander);
    this.type = type;
  }

  public UnstructuredSupplementaryServiceDataCommand(final AtCommander atCommander, final Integer type,
                                                     final String ussdString) {
    super(COMMAND_CUSD, atCommander);
    this.type = type;
    this.ussdString = ussdString;
  }

  public UnstructuredSupplementaryServiceDataCommand(final AtCommander atCommander,
                                                     final Integer type,
                                                     final String ussdString,
                                                     final Integer dcs) {
    super(COMMAND_CUSD, atCommander);
    this.type = type;
    this.ussdString = ussdString;
    this.dcs = dcs;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CUSD);
      sb.append(EQUAL);
      if (type != null) {
        sb.append(type);
        if (ussdString != null) {
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(ussdString);
          sb.append(DOUBLE_QUOTE);
          if (dcs != null) {
            sb.append(COMMA);
            sb.append(dcs);
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public UnstructuredSupplementaryServiceDataResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CUSD);
      sb.append(QUERY);
      return new UnstructuredSupplementaryServiceDataResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public UnstructuredSupplementaryServiceDataTestResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CUSD);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new UnstructuredSupplementaryServiceDataTestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
