package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class GprsEventReportingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CGEV: ([a-zA-Z0-9][a-zA-Z0-9 ]*)(, \"([0-9.]*)\", (\\d))?$");

  public static final String EVENT_ME_DEACT_IP = "ME DEACT IP";
  public static final String EVENT_NW_DEACT_IP = "NW DEACT IP";
  public static final String EVENT_NW_REACT_IP = "NW REACT IP";
  public static final String EVENT_REJECT_IP = "REJECT IP";
  public static final String EVENT_NW_DETACH = "NW DETACH";
  public static final String EVENT_ME_DETACH = "ME DETACH";
  public static final String EVENT_ME_CLASS = "ME CLASS";

  private String event;
  private String address;
  private Integer cid;

  public GprsEventReportingUnsolicited() {
  }

  @Override
  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        event = m.group(1);
        // optional address and cid
        if (m.group(2) != null) {
          address = m.group(3);
          cid = getInteger(m.group(4));
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getEvent() {
    return event;
  }

  public String getAddress() {
    return address;
  }

  public Integer getCid() {
    return cid;
  }
}
