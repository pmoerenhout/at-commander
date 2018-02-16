package org.atcommander.module._3gpp.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.exceptions.ParseException;

public class ServiceReportingControlResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CR: (.*)$");

  private String service;

  public ServiceReportingControlResponse(final String s) {
    parse(s);
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      service = m.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + response);
  }

  public String getService() {
    return service;
  }
}