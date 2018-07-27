package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class NoCarrierResponseTest {

  @Test
  public void test_no_carrier_with_socketid() throws Exception {
    final NoCarrierResponse noCarrierResponse = new NoCarrierResponse();
    noCarrierResponse.parseUnsolicited(Collections.singletonList("NO CARRIER:3"));

    assertEquals(new Integer(3), noCarrierResponse.getSocketId());
    assertEquals(null, noCarrierResponse.getCause());
  }

  @Test
  public void test_no_carrier_with_socketid_and_cause() throws Exception {
    final NoCarrierResponse noCarrierResponse = new NoCarrierResponse();
    noCarrierResponse.parseUnsolicited(Collections.singletonList("NO CARRIER:2,1"));

    assertEquals(new Integer(2), noCarrierResponse.getSocketId());
    assertEquals(new Integer(1), noCarrierResponse.getCause());
  }
}