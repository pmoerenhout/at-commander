package com.github.pmoerenhout.atcommander.module.v250.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class MobileEquipmentErrorResponseTest {
  @Test
  public void test_cme_error_1() throws Exception {
    final String line = "+CME ERROR: requested service option not subscribed (#33)";

    final MobileEquipmentErrorResponse mobileEquipmentErrorResponse = new MobileEquipmentErrorResponse(line);

    assertEquals("requested service option not subscribed", mobileEquipmentErrorResponse.getMessage());
    assertEquals(Integer.valueOf(33), mobileEquipmentErrorResponse.getCode());
  }

  @Test
  public void test_cme_error_2() throws Exception {
    final String line = "+CME ERROR: stack already active";

    final MobileEquipmentErrorResponse mobileEquipmentErrorResponse = new MobileEquipmentErrorResponse(line);

    assertEquals("stack already active", mobileEquipmentErrorResponse.getMessage());
    assertNull(mobileEquipmentErrorResponse.getCode());
  }
}