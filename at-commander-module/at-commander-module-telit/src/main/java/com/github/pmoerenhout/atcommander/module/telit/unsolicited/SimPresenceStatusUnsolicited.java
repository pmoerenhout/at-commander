package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class SimPresenceStatusUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#SIMPR: ([0-1]),([0-1])$");

  private Integer remote;
  private Integer inserted;

  public SimPresenceStatusUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m1 = UNSOLICITED_PATTERN.matcher(line);
      if (m1.find()) {
        remote = Integer.valueOf(m1.group(1));
        inserted = Integer.valueOf(m1.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public Integer getRemote() {
    return remote;
  }

  public Integer getInserted() {
    return inserted;
  }
}
