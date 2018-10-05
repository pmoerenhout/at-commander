package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class SmsOverflowUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public final static Pattern UNSOLICITED_PATTERN = Pattern.compile("^#SMOV: \"(.*)\"$");

  // SM or ME
  private String memo;

  public SmsOverflowUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        memo = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getMemo() {
    return memo;
  }
}
