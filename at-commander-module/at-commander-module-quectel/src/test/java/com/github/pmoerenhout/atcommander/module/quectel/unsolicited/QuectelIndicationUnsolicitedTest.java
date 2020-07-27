package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class QuectelIndicationUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_qind_without_quotes() {
    final String line = "+QIND: SMS DONE";

    assertPatternMatch(QuectelIndicationUnsolicited.UNSOLICITED_PATTERN, line);

    final QuectelIndicationUnsolicited quectelIndicationUnsolicited = new QuectelIndicationUnsolicited();
    quectelIndicationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("SMS DONE", quectelIndicationUnsolicited.getIndication());
  }

  @Test
  public void test_qind_with_quotes() {
    final String line = "+QIND: \"SIM REFRESH\"";

    assertPatternMatch(QuectelIndicationUnsolicited.UNSOLICITED_PATTERN, line);

    final QuectelIndicationUnsolicited quectelIndicationUnsolicited = new QuectelIndicationUnsolicited();
    quectelIndicationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("SIM REFRESH", quectelIndicationUnsolicited.getIndication());
  }

}