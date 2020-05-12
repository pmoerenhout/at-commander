package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class IndicatorEventUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +CIEV: rssi,4
  public final static Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CIEV: ([a-z]*),(\\d*)$");

  private String indicator;
  private int status;

  public IndicatorEventUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        indicator = m.group(1);
        status = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getIndicator() {
    return indicator;
  }

  public int getStatus() {
    return status;
  }
}
