package com.github.pmoerenhout.atcommander.module.v250.unsolicited;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class RingUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_ring() {
    final String line = "RING";

    assertPatternMatch(RingUnsolicited.UNSOLICITED_PATTERN, line);

    final RingUnsolicited ringUnsolicited = new RingUnsolicited();
    ringUnsolicited.parseUnsolicited(Collections.singletonList(line));
  }
}