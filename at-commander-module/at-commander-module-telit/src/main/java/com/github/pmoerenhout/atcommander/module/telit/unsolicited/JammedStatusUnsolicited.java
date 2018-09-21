package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class JammedStatusUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#JDR: (.*)$");

  private String status;

  public JammedStatusUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        status = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getStatus() {
    return status;
  }
}
