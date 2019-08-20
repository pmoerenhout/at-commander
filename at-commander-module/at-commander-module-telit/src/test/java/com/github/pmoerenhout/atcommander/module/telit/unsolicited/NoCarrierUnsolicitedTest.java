package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class NoCarrierUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_no_carrier_with_socketid() throws Exception {
    final String line = "NO CARRIER:3";

    assertPatternMatch(NoCarrierUnsolicited.UNSOLICITED_PATTERN, line);

    final NoCarrierUnsolicited noCarrierResponse = new NoCarrierUnsolicited();
    noCarrierResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(3), noCarrierResponse.getSocketId());
    assertNull(noCarrierResponse.getCause());
  }

  @Test
  public void test_no_carrier_with_socketid_and_cause() throws Exception {
    final String line = "NO CARRIER:2,1";

    assertPatternMatch(NoCarrierUnsolicited.UNSOLICITED_PATTERN, line);

    final NoCarrierUnsolicited noCarrierResponse = new NoCarrierUnsolicited();
    noCarrierResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(2), noCarrierResponse.getSocketId());
    assertEquals(Integer.valueOf(1), noCarrierResponse.getCause());
  }
}