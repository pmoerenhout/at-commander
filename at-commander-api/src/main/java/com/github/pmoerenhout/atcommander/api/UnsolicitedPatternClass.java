package com.github.pmoerenhout.atcommander.api;

import java.util.regex.Pattern;

public class UnsolicitedPatternClass {
  final private Pattern pattern;
  final private int additionalLines;
  final private Class clazz;

  public UnsolicitedPatternClass(final Pattern pattern, final Class clazz) {
    this.pattern = pattern;
    this.additionalLines = 0;
    this.clazz = clazz;
  }

  public UnsolicitedPatternClass(final Pattern pattern, final Class clazz, final int additionalLines) {
    this.pattern = pattern;
    this.additionalLines = additionalLines;
    this.clazz = clazz;
  }

  public Pattern getPattern() {
    return pattern;
  }

  public Class getClazz() {
    return clazz;
  }

  public int getNumberOfAdditionalLines() {
    return additionalLines;
  }
}

