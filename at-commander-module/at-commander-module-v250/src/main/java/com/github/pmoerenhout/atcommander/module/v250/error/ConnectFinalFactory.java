package com.github.pmoerenhout.atcommander.module.v250.error;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;
import com.github.pmoerenhout.atcommander.FinalResponseFactory;

public class ConnectFinalFactory implements FinalResponseFactory {

  private static final Pattern PATTERN_CONNECT = Pattern.compile("^CONNECT$");

  public AbstractFinalResponse generate(final String line) {
    final Matcher matcher = PATTERN_CONNECT.matcher(line);
    if (matcher.find()) {
      return new ConnectFinalResponseCode(line);
    }
    return null;
  }
}
