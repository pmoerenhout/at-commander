package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class QuectelPsmTimerUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_quectel_psm_timer() {
    final String line = "+QPSMTIMER: 1320,30";

    assertPatternMatch(QuectelPsmTimerUnsolicited.UNSOLICITED_PATTERN, line);

    final QuectelPsmTimerUnsolicited quectelPsmTimerUnsolicited = new QuectelPsmTimerUnsolicited();
    quectelPsmTimerUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(1320, quectelPsmTimerUnsolicited.getTau());
    assertEquals(30, quectelPsmTimerUnsolicited.getT3324());
  }

}