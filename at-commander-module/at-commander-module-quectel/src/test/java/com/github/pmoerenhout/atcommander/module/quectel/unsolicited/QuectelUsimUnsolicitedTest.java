package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class QuectelUsimUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_qusim() {
    final String line = "+QUSIM: 1";

    assertPatternMatch(QuectelUsimUnsolicited.UNSOLICITED_PATTERN, line);

    final QuectelUsimUnsolicited quectelUsimUnsolicited = new QuectelUsimUnsolicited();
    quectelUsimUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(1, quectelUsimUnsolicited.getUsim());
  }
}