package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.types.FacilityStatus;

public class FacilityLockResponseTest extends BaseCommandTest {

  @Test
  public void test_facility_status() throws Exception {
    final AtResponse response = createOkAtResponse("+CLCK: 1");

    final FacilityLockResponse facilityLockResponse = new FacilityLockResponse(response);

    final FacilityStatus facilityStatus = facilityLockResponse.getFacilityStatus()[0];
    assertEquals(1, facilityStatus.getStatus());
    assertNull(facilityStatus.getClazz());
  }

  @Test
  public void test_facility_status_with_class() throws Exception {
    final AtResponse response = createOkAtResponse("+CLCK: 0,3");

    final FacilityLockResponse facilityLockResponse = new FacilityLockResponse(response);

    final FacilityStatus facilityStatus = facilityLockResponse.getFacilityStatus()[0];
    assertEquals(0, facilityStatus.getStatus());
    assertEquals(Integer.valueOf(3), facilityStatus.getClazz());
  }

  @Test
  public void test_facility_status_with_class_() throws Exception {
    final AtResponse response = createAtResponse(new String[]{ "+CLCK: 1,1", "+CLCK: 0,2", "OK" });

    final FacilityLockResponse facilityLockResponse = new FacilityLockResponse(response);

    final FacilityStatus facilityStatus1 = facilityLockResponse.getFacilityStatus()[0];
    assertEquals(1, facilityStatus1.getStatus());
    assertEquals(Integer.valueOf(1), facilityStatus1.getClazz());

    final FacilityStatus facilityStatus2 = facilityLockResponse.getFacilityStatus()[1];
    assertEquals(0, facilityStatus2.getStatus());
    assertEquals(Integer.valueOf(2), facilityStatus2.getClazz());
  }

}