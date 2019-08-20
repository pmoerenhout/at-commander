package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class DisconnectedUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_disconnected() throws Exception {
    final String line = "DISCONNECTED";

    assertPatternMatch(DisconnectedUnsolicited.UNSOLICITED_PATTERN, line);

    final DisconnectedUnsolicited disconnected = new DisconnectedUnsolicited();
    disconnected.parseUnsolicited(Collections.singletonList("DISCONNECTED"));
  }
}