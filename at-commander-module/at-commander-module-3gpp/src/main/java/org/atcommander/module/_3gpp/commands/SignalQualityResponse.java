package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class SignalQualityResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CSQ: (\\d*),(\\d*)$");

  private int rssi;
  private int ber;

  public SignalQualityResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        rssi = Integer.parseInt(m.group(1));
        ber = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getRssi() {
    return rssi;
  }

  public int getBer() {
    return ber;
  }

}
