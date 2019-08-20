package com.github.pmoerenhout.atcommander;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum FinalResponseCode {

  // https://en.wikibooks.org/wiki/Serial_Programming/Modems_and_AT_Commands/Result_Codes

  EMPTY("^\r\n$", 0),
  OK("^OK$", 0),
  CONNECT("^CONNECT\\s?(.*)$", 1),
  ERROR("^ERROR$", 0),
  NO_CARRIER("^NO CARRIER$", 0),
  NO_DIALTONE("^NO DIALTONE$", 0),
  BUSY("^BUSY$", 0),
  NO_ANSWER("^NO ANSWER$", 0),
  CONNECTED("^CONNECTED$", 0),
  RELEASED("^RELEASED$", 0),
  CME_ERROR("^\\+CME ERROR: (.*)$", 1),
  CMS_ERROR("^\\+CMS ERROR: (.*)$", 1),
  MORE_DATA("^> $", 0);

  final Pattern pattern;
  final int groupCount;

  FinalResponseCode(final String regex, final int parameters) {
    this.pattern = Pattern.compile(regex);
    this.groupCount = parameters;
  }

  public static FinalResponseCode fromString(final String response) {
    if (response != null) {
      for (FinalResponseCode finalResponseCode : FinalResponseCode.values()) {
        final Matcher m = finalResponseCode.pattern.matcher(response);
        if (m.find()) {
          return finalResponseCode;
        }
      }
    }
    return null;
  }

  public static FinalResponse fromStringEx(final String response) {
    if (response != null) {
      for (final FinalResponseCode b : FinalResponseCode.values()) {
        final Matcher m = b.pattern.matcher(response);
        if (m.find()) {
          // LOG.debug("Regex match found for '{}' ({})", response, b.getPattern().toString());
          return new FinalResponse(b, (b.groupCount == 0 ? null : m.group(1)));
        }
      }
    }
    return null;
  }

  public Pattern getPattern() {
    return this.pattern;
  }

  public int getGroupCount() {
    return this.groupCount;
  }
}
