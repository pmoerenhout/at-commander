package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class DialingUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_dialing() throws Exception {
    final String line = "DIALING";

    assertPatternMatch(DialingUnsolicited.UNSOLICITED_PATTERN, line);

    final DialingUnsolicited dialing = new DialingUnsolicited();
    dialing.parseUnsolicited(Collections.singletonList(line));
  }
}