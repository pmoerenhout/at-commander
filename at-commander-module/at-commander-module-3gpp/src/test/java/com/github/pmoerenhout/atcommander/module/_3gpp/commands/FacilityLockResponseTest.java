package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class FacilityLockResponseTest extends BaseCommandTest {

  @Test
  public void testFacilityStatus() throws Exception {
    final AtResponse response = createOkAtResponse("+CLCK: 2");

    final FacilityLockResponse facilityLockResponse = new FacilityLockResponse(response);

    assertEquals(2, facilityLockResponse.getFacilityStatus()[0].getStatus());
    assertNull(facilityLockResponse.getFacilityStatus()[0].getClazz());
  }

  @Test
  public void testFacilityStatus2() throws Exception {
    final AtResponse response = createOkAtResponse("+CLCK: 2,3");

    final FacilityLockResponse facilityLockResponse = new FacilityLockResponse(response);

    assertEquals(2, facilityLockResponse.getFacilityStatus()[0].getStatus());
    assertEquals(new Integer(3), facilityLockResponse.getFacilityStatus()[0].getClazz());
  }

}