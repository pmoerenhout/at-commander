package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationResponseTest extends BaseCommandTest {

  @Test
  public void test_creg_solicited_2_2() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,2");

    final NetworkRegistrationResponse networkRegistrationResponse = new NetworkRegistrationResponse(response);

    assertEquals(2, networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.SEARCHING, networkRegistrationResponse.getRegistrationState());
    assertFalse(networkRegistrationResponse.getLac().isPresent());
    assertFalse(networkRegistrationResponse.getCellId().isPresent());
    assertFalse(networkRegistrationResponse.getAccessTechnology().isPresent());
  }

  @Test
  public void test_creg_solicited_2_3() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,3");

    final NetworkRegistrationResponse networkRegistrationResponse = new NetworkRegistrationResponse(response);

    assertEquals(2, networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.DENIED, networkRegistrationResponse.getRegistrationState());
    assertFalse(networkRegistrationResponse.getLac().isPresent());
    assertFalse(networkRegistrationResponse.getCellId().isPresent());
    assertFalse(networkRegistrationResponse.getAccessTechnology().isPresent());
  }

  @Test
  public void test_creg_2_5_with_lac_and_cellid() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,5,\"9BCD\",\"09AB\"");

    final NetworkRegistrationResponse networkRegistrationResponse = new NetworkRegistrationResponse(response);

    assertEquals(2, networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistrationResponse.getRegistrationState());
    assertEquals(Integer.valueOf(0x9bcd), networkRegistrationResponse.getLac().get());
    assertEquals(Integer.valueOf(0x09ab), networkRegistrationResponse.getCellId().get());
    assertFalse(networkRegistrationResponse.getAccessTechnology().isPresent());
  }

  @Test
  public void test_creg_2_1_with_lac_and_cellid_and_act() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,1,\"9BCD\",\"09AB\",2");

    final NetworkRegistrationResponse networkRegistrationResponse = new NetworkRegistrationResponse(response);

    assertEquals(2, networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
    assertEquals(Integer.valueOf(0x9bcd), networkRegistrationResponse.getLac().get());
    assertEquals(Integer.valueOf(0x09ab), networkRegistrationResponse.getCellId().get());
    assertEquals(AccessTechnology.UTRAN, networkRegistrationResponse.getAccessTechnology().get());
  }

}