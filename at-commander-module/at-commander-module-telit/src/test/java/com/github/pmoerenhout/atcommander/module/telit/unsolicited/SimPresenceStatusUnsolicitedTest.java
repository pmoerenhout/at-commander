package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class SimPresenceStatusUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_simpr_unsolicited() {
    final String line = "#SIMPR: 0,1";

    assertPatternMatch(SimPresenceStatusUnsolicited.UNSOLICITED_PATTERN, line);

    final SimPresenceStatusUnsolicited simPresenceStatus = new SimPresenceStatusUnsolicited();
    simPresenceStatus.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(0), simPresenceStatus.getRemote());
    assertEquals(Integer.valueOf(1), simPresenceStatus.getInserted());
  }
}