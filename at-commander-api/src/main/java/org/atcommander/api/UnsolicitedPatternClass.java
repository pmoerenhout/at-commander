package org.atcommander.api;

import java.util.regex.Pattern;

public class UnsolicitedPatternClass {
  final Pattern pattern;
  final Class clazz;

  public UnsolicitedPatternClass(final Pattern pattern, final Class clazz) {
    this.pattern = pattern;
    this.clazz = clazz;
  }

  public Pattern getPattern() {
    return pattern;
  }

  public Class getClazz() {
    return clazz;
  }
}

