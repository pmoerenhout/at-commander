package com.github.pmoerenhout.atcommander.module.v250.error;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.FinalResponseFactory;
import com.github.pmoerenhout.atcommander.FinalResponse2;

public class ConnectFinalFactory implements FinalResponseFactory {

  private static final Pattern PATTERN_CONNECT = Pattern.compile("^CONNECT$");

  public FinalResponse2 generate(final String line) {
    final Matcher matcher = PATTERN_CONNECT.matcher(line);
    if (matcher.find()) {
      return new ConnectFinalResponseCode(line);
    }
    return null;
  }
}