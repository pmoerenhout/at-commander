package com.github.pmoerenhout.atcommander.module.v250.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class RingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern PATTERN = Pattern.compile("^RING$");

  public RingUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m1 = PATTERN.matcher(line);
      if (m1.find()) {
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }
}
