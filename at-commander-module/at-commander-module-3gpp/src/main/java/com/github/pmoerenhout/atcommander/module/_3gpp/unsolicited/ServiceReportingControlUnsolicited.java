package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class ServiceReportingControlUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // Intermediate during connect negotiaition
  // +CR: <ASYNC|SYNC|REL ASYNC|REL SYNC>
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CR: (.*)$");

  private String service;

  public ServiceReportingControlUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        service = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getService() {
    return service;
  }
}