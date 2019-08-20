package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class JammedStatusUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_jammed_status() throws Exception {
    final String line = "#JDR: Blocked";

    assertPatternMatch(JammedStatusUnsolicited.UNSOLICITED_PATTERN, line);

    final JammedStatusUnsolicited jammedStatusUnsolicited = new JammedStatusUnsolicited();
    jammedStatusUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("Blocked", jammedStatusUnsolicited.getStatus());
  }
}