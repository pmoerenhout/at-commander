package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

@Unsolicited
public class QuerySimStatusUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#QSS: (\\d)$");

  private SimStatus status;

  public QuerySimStatusUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        status = SimStatus.fromInt(Integer.parseInt(m.group(1)));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public SimStatus getStatus() {
    return status;
  }
}
