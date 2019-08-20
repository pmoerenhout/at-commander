package com.github.pmoerenhout.atcommander.basic.unsolicited;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

public abstract class UnsolicitedTest {

  protected void assertPatternMatch(final Pattern pattern, final String line) {
    final Matcher matcher = pattern.matcher(line);
    Assert.assertTrue(matcher.matches());
  }
}
