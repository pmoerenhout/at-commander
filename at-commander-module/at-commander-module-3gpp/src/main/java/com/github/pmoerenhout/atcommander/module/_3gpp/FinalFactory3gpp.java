package com.github.pmoerenhout.atcommander.module._3gpp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.FinalResponse2;
import com.github.pmoerenhout.atcommander.FinalResponseFactory;

public class FinalFactory3gpp implements FinalResponseFactory {

  // ETSI TS 300 916

  private static final Pattern PATTERN_CME_ERROR = Pattern.compile("^\\+CME ERROR: (.*)$");

  public FinalResponse2 generate(final String line) {

    final Matcher matcherConnect = PATTERN_CME_ERROR.matcher(line);
    if (matcherConnect.find()) {
      final String text = matcherConnect.group(1);
      return new CmeErrorFinalResponse(line, text);
    }
    return null;
  }
}
//
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