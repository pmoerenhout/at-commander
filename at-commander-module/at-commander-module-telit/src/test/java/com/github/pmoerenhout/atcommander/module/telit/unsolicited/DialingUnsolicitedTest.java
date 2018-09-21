package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.Collections;

import org.junit.Test;

public class DialingUnsolicitedTest {

  @Test
  public void test_dialing() throws Exception {
    final DialingUnsolicited dialing = new DialingUnsolicited();
    dialing.parseUnsolicited(Collections.singletonList("DIALING"));
  }
}