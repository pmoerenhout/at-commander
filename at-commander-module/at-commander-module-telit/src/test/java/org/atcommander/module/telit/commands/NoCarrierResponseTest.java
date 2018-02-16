package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoCarrierResponseTest {

  @Test
  public void testParse2() throws Exception {
    final NoCarrierResponse noCarrierResponse = new NoCarrierResponse("NO CARRIER:3");

    assertEquals(new Integer(3), noCarrierResponse.getSocketId());
    assertEquals(null, noCarrierResponse.getCause());
  }

  @Test
  public void testParse3() throws Exception {
    final NoCarrierResponse noCarrierResponse = new NoCarrierResponse("NO CARRIER:2,1");

    assertEquals(new Integer(2), noCarrierResponse.getSocketId());
    assertEquals(new Integer(1), noCarrierResponse.getCause());
  }
}