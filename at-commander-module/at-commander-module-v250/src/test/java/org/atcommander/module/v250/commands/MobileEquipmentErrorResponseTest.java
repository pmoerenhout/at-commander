package org.atcommander.module.v250.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MobileEquipmentErrorResponseTest {
  @Test
  public void testCmeError() throws Exception {
    final String line = "+CME ERROR: requested service option not subscribed (#33)";

    final MobileEquipmentErrorResponse mobileEquipmentErrorResponse = new MobileEquipmentErrorResponse(line);

    assertEquals("requested service option not subscribed", mobileEquipmentErrorResponse.getMessage());
    assertEquals(new Integer(33), mobileEquipmentErrorResponse.getCode());
  }

  @Test
  public void testCmeError2() throws Exception {
    final String line = "+CME ERROR: stack already active";

    final MobileEquipmentErrorResponse mobileEquipmentErrorResponse = new MobileEquipmentErrorResponse(line);

    assertEquals("stack already active", mobileEquipmentErrorResponse.getMessage());
    assertEquals(null, mobileEquipmentErrorResponse.getCode());
  }
}