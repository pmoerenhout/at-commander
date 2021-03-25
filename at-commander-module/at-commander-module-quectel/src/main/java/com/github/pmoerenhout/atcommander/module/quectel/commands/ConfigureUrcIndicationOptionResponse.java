package com.github.pmoerenhout.atcommander.module.quectel.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ConfigureUrcIndicationOptionResponse extends BaseResponse implements Response {

  //+QURCCFG: "urcport","usbat"

  // "usbat" USB AT port "usbmodem" USB modem port "uart1" Main UART

  private static final Pattern PATTERN = Pattern.compile("^\\+QURCCFG: \"urcport\",\"(.*)\"$");

  private String urcport;

  public ConfigureUrcIndicationOptionResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        urcport = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getUrcport() {
    return urcport;
  }
}
