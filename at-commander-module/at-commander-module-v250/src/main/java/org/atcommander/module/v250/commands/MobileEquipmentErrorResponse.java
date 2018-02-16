package org.atcommander.module.v250.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.basic.exceptions.ParseException;

public class MobileEquipmentErrorResponse {

  // +CME ERROR: requested service option not subscribed (#33)
  // +CME ERROR: stack already active

  private static final Pattern PATTERN1 = Pattern.compile("^\\+CME ERROR: (.*) \\(#(\\d*)\\)$");
  private static final Pattern PATTERN2 = Pattern.compile("^\\+CME ERROR: (.*)$");

  private String message;
  private Integer code;

  public MobileEquipmentErrorResponse(final String s) {
    parse(s);
  }

  public void parse(final String line) {
    final Matcher m1 = PATTERN1.matcher(line);
    if (m1.find()) {
      message = m1.group(1);
      code = Integer.parseInt(m1.group(2));
      return;
    }
    final Matcher m2 = PATTERN2.matcher(line);
    if (m2.find()) {
      message = m2.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + line);
  }

  public String getMessage() {
    return message;
  }

  public Integer getCode() {
    return code;
  }
}
