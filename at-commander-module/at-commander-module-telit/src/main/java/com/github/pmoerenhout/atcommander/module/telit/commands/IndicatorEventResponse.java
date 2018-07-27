package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class IndicatorEventResponse extends BaseResponse implements UnsolicitedResponse {

  // +CIEV: rssi,4
  public final static Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CIEV: ([a-z]*),(\\d*)$");

  private String indicator;
  private int status;

  public IndicatorEventResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  private void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      indicator = m.group(1);
      status = Integer.parseInt(m.group(2));
      return;
    }
    throw createParseException(line);
  }

  public String getIndicator() {
    return indicator;
  }

  public int getStatus() {
    return status;
  }
}
