package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class GprsNetworkRegistrationResponseTest extends BaseCommandTest {

  @Test
  public void test_gprs_network_registration() throws Exception {
    final AtResponse response = createOkAtResponse("+CGREG: 0,1");

    final GprsNetworkRegistrationResponse gprsNetworkRegistration = new GprsNetworkRegistrationResponse(response);

    assertEquals(Integer.valueOf(0), gprsNetworkRegistration.getPresentationMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistration.getRegistrationState());
  }

  @Test
  public void test_gprs_network_registration_location_information() throws Exception {
    final AtResponse response = createOkAtResponse("+CGREG: 2,1,\"00DE\",\"0A5C\"");

    final GprsNetworkRegistrationResponse gprsNetworkRegistration = new GprsNetworkRegistrationResponse(response);

    assertEquals(Integer.valueOf(2), gprsNetworkRegistration.getPresentationMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistration.getRegistrationState());
    assertEquals("00DE", gprsNetworkRegistration.getLac());
    assertEquals("0A5C", gprsNetworkRegistration.getCid());
    assertNull(gprsNetworkRegistration.getAccessTechnology());
    assertNull(gprsNetworkRegistration.getRac());
  }

  @Test
  public void test_gprs_network_registration_location_information_and_access() throws Exception {
    final AtResponse response = createOkAtResponse("+CGREG: 2,5,\"00DE\",\"0A5B\",0,\"01\"");

    final GprsNetworkRegistrationResponse gprsNetworkRegistration = new GprsNetworkRegistrationResponse(response);

    assertEquals(Integer.valueOf(2), gprsNetworkRegistration.getPresentationMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistration.getRegistrationState());
    assertEquals("00DE", gprsNetworkRegistration.getLac());
    assertEquals("0A5B", gprsNetworkRegistration.getCid());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistration.getAccessTechnology());
    assertEquals("01", gprsNetworkRegistration.getRac());
  }
}