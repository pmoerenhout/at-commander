package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class GprsEventReportingResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CGEV: (.*)$");

  public static final String EVENT_ME_DEACT_IP = "ME DEACT IP";
  public static final String EVENT_NW_DEACT_IP = "NW DEACT IP";
  public static final String EVENT_NW_REACT_IP = "NW REACT IP";
  public static final String EVENT_REJECT_IP = "REJECT IP";
  public static final String EVENT_NW_DETACH = "NW DETACH";
  public static final String EVENT_ME_DETACH = "ME DETACH";
  public static final String EVENT_ME_CLASS = "ME CLASS";

  private static final Pattern PATTERN1 = Pattern.compile("^\\+CGEV: ([a-zA-Z0-9][a-zA-Z0-9 ]*)$");
  private static final Pattern PATTERN3 = Pattern.compile("^\\+CGEV: ([a-zA-Z0-9][a-zA-Z0-9 ]*), \"([0-9.]*)\", (\\d)$");

  private String event;
  private String address;
  private Integer cid;

  public GprsEventReportingResponse(final String line) {
    parse(line);
  }

  public void parse(final String line) {

    final Matcher m1 = PATTERN1.matcher(line);
    if (m1.find()) {
      event = m1.group(1);
      return;
    }
    final Matcher m3 = PATTERN3.matcher(line);
    if (m3.find()) {
      event = m3.group(1);
      address = m3.group(2);
      cid = Integer.parseInt(m3.group(3));
      return;
    }
    throw new ParseException("Could not parse response: " + line);
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
