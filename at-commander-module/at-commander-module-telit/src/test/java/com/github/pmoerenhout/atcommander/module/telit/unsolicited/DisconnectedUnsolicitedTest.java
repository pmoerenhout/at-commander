package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.Collections;

import org.junit.Test;

public class DisconnectedUnsolicitedTest {

  @Test
  public void test_disconnected() throws Exception {
    final DisconnectedUnsolicited disconnected = new DisconnectedUnsolicited();
    disconnected.parseUnsolicited(Collections.singletonList("DISCONNECTED"));
  }
}