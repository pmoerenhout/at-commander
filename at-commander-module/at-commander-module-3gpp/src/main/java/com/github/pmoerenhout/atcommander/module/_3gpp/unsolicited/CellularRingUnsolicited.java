package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class CellularRingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +CRING: <DATA|FAX|VOICE>
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CRING: (.*)$");

  private String type;

  public CellularRingUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      type = m.group(1);
      return;
    }
    throw createParseException(response);
  }

  public String getType() {
    return type;
  }
}