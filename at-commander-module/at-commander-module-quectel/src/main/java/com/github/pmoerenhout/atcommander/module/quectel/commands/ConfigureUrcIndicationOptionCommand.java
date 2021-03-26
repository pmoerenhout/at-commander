package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ConfigureUrcIndicationOptionCommand extends BaseCommand implements Command<BaseResponse> {

  public static final String URC_PORT_USB_BAT = "usbbat";
  public static final String URC_PORT_USB_MODEM = "usbmodem";
  public static final String URC_PORT_UART1 = "uart1";

  private static final String COMMAND_QUECTEL_CONFIGURE_URC_INDICATION_OPTION = "+QURCCFG";

  private static final String URC_PORT = "urcport";

  private String urcPort;

  public ConfigureUrcIndicationOptionCommand(final AtCommander atCommander) {
    super(COMMAND_QUECTEL_CONFIGURE_URC_INDICATION_OPTION, atCommander);
  }

  public ConfigureUrcIndicationOptionCommand(final AtCommander atCommander, final String urcPort) {
    super(COMMAND_QUECTEL_CONFIGURE_URC_INDICATION_OPTION, atCommander);
    this.urcPort = urcPort;
  }

  @Override
  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_CONFIGURE_URC_INDICATION_OPTION);
      sb.append(EQUAL);
      sb.append(doubleQuoted(URC_PORT));
      sb.append(COMMA);
      sb.append(doubleQuoted(urcPort));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  @Override
  public ConfigureUrcIndicationOptionResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_CONFIGURE_URC_INDICATION_OPTION);
      sb.append(EQUAL);
      sb.append(doubleQuoted(URC_PORT));
      return new ConfigureUrcIndicationOptionResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
