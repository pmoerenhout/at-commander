package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

public class NoCarrierUnsolicitedTest {

  @Test
  public void test_no_carrier_with_socketid() throws Exception {
    final NoCarrierUnsolicited noCarrierResponse = new NoCarrierUnsolicited();
    noCarrierResponse.parseUnsolicited(Collections.singletonList("NO CARRIER:3"));

    assertEquals(Integer.valueOf(3), noCarrierResponse.getSocketId());
    assertNull(noCarrierResponse.getCause());
  }

  @Test
  public void test_no_carrier_with_socketid_and_cause() throws Exception {
    final NoCarrierUnsolicited noCarrierResponse = new NoCarrierUnsolicited();
    noCarrierResponse.parseUnsolicited(Collections.singletonList("NO CARRIER:2,1"));

    assertEquals(Integer.valueOf(2), noCarrierResponse.getSocketId());
    assertEquals(Integer.valueOf(1), noCarrierResponse.getCause());
  }
}