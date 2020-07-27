package com.github.pmoerenhout.atcommander.module.quectel.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class TemperatureResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+QTEMP: (\\d*),(\\d*),(\\d*)$");

  private int pmicTemp;
  private int xoTemp;
  private int paTemp;

  public TemperatureResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        pmicTemp = Integer.parseInt(m.group(1));
        xoTemp = Integer.parseInt(m.group(2));
        paTemp = Integer.parseInt(m.group(3));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getPmicTemp() {
    return pmicTemp;
  }

  public int getXoTemp() {
    return xoTemp;
  }

  public int getPaTemp() {
    return paTemp;
  }
}
