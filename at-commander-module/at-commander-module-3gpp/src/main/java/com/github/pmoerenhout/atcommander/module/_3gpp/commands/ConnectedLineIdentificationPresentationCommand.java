package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ConnectedLineIdentificationPresentationCommand extends BaseCommand implements Command<BaseResponse> {

  // AT+CLIP=<0|1>

  private static final String COMMAND_CONNECTED_LINE_IDENTIFICATION_PRESENTATION = "+COLP";

  private boolean connectedLineIndicationPresentation;

  public ConnectedLineIdentificationPresentationCommand(final AtCommander atCommander) {
    super(COMMAND_CONNECTED_LINE_IDENTIFICATION_PRESENTATION, atCommander);
  }

  public ConnectedLineIdentificationPresentationCommand(final AtCommander atCommander, final boolean connectedLineIndicationPresentation) {
    super(COMMAND_CONNECTED_LINE_IDENTIFICATION_PRESENTATION, atCommander);
    this.connectedLineIndicationPresentation = connectedLineIndicationPresentation;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CONNECTED_LINE_IDENTIFICATION_PRESENTATION);
      sb.append(EQUAL);
      sb.append(oneOrZero(connectedLineIndicationPresentation));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public CallingLineIdentificationPresentationResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CONNECTED_LINE_IDENTIFICATION_PRESENTATION);
      sb.append(QUERY);
      return new CallingLineIdentificationPresentationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}