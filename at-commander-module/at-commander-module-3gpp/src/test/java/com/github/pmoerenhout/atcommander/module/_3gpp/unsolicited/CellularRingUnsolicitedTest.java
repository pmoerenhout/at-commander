package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class CellularRingUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_cellular_ring_data() {
    final String line = "+CRING: DATA";

    assertPatternMatch(CellularRingUnsolicited.UNSOLICITED_PATTERN, line);

    final CellularRingUnsolicited cellularRingUnsolicited = new CellularRingUnsolicited();
    cellularRingUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("DATA", cellularRingUnsolicited.getType());
  }
}