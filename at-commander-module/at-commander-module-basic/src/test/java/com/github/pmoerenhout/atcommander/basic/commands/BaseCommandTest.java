package com.github.pmoerenhout.atcommander.basic.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.BasicFinalFactory;

public abstract class BaseCommandTest {

  protected AtResponse createOkAtResponse(final String line) {
    final List<String> lines = new ArrayList<>();
    lines.add(line);
    lines.add("OK");
    return new AtResponse(Collections.singletonList(new BasicFinalFactory()), lines);
  }

  protected AtResponse createOkAtResponse(final List<String> lines) {
    return new AtResponse(Collections.singletonList(new BasicFinalFactory()), lines);
  }

  protected AtResponse createAtResponse(final String[] lines) {
    final List<String> list = new ArrayList<>();
    for (final String s : lines) {
      list.add(s);
    }
    return new AtResponse(Collections.singletonList(new BasicFinalFactory()), list);
  }

  protected void assertPatternMatch(final Pattern pattern, final String line) {
    final Matcher matcher = pattern.matcher(line);
    Assert.assertTrue(matcher.matches());
  }
}
