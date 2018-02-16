package org.atcommander.module.v250;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.atcommander.FinalResponse2;
import org.atcommander.FinalResponseFactory;
import org.atcommander.module.v250.types.DialStatus;

public class V250FinalFactory implements FinalResponseFactory {

  private static final String STRING_BUSY = "BUSY";
  private static final String STRING_CONNECT = "CONNECT";
  private static final Pattern PATTERN_CONNECT_TEXT = Pattern.compile("^CONNECT (.*)$");
  private static final String STRING_ERROR = "ERROR";
  private static final String STRING_NO_CARRIER = "NO CARRIER";
  private static final String STRING_NO_ANSWER = "NO ANSWER";
  private static final String STRING_NO_DIALTONE = "NO DIALTONE";

  public FinalResponse2 generate(final String line) {
    if (StringUtils.equals(STRING_NO_CARRIER, line)) {
      return new DialFinalResponse(line, DialStatus.NO_CARRIER);
    }
    if (StringUtils.equals(STRING_CONNECT, line)) {
      return new DialFinalResponse(line, DialStatus.CONNECT);
    }
    final Matcher matcherConnect = PATTERN_CONNECT_TEXT.matcher(line);
    if (matcherConnect.find()) {
      return new DialFinalResponse(line, DialStatus.CONNECT, matcherConnect.group(1));
    }
    if (StringUtils.equals(STRING_ERROR, line)) {
      return new DialFinalResponse(line, DialStatus.ERROR);
    }
    if (StringUtils.equals(STRING_BUSY, line)) {
      return new DialFinalResponse(line, DialStatus.BUSY);
    }
    if (StringUtils.equals(STRING_NO_ANSWER, line)) {
      return new DialFinalResponse(line, DialStatus.NO_ANSWER);
    }
    if (StringUtils.equals(STRING_NO_DIALTONE, line)) {
      return new DialFinalResponse(line, DialStatus.NO_DIALTONE);
    }
    return null;
  }
}
//
//  EMPTY("^\r\n$", 0),
//  OK("^OK$", 0),
//  CONNECT("^CONNECT (.*)$", 1),
//  ERROR("^ERROR$", 0),
//  NO_CARRIER("^NO CARRIER$", 0),
//  NO_DIALTONE("^NO DIALTONE$", 0),
//  BUSY("^BUSY$", 0),
//  NO_ANSWER("^NO ANSWER$", 0),
//  CONNECTED("^CONNECTED$", 0),
//  RELEASED("^RELEASED$", 0),
//  CME_ERROR("^\\+CME ERROR: (.*)$", 1),
//  CMS_ERROR("^\\+CMS ERROR: (.*)$", 1),
//  MORE_DATA("^> $", 0);