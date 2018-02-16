package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module._3gpp.RegistrationState;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.junit.Test;


public class GprsNetworkRegistrationResponseTest extends BaseCommandTest {

  @Test
  public void test_unsolicited_1() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 1");

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_unsolicited_2() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 2");

    assertNull(gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.SEARCHING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertNull(gprsNetworkRegistrationResponse.getLac());
    assertNull(gprsNetworkRegistrationResponse.getCellId());
  }

  @Test
  public void test_2_5() {
    final String line = "+CGREG: 2,5";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertNull(gprsNetworkRegistrationResponse.getLac());
    assertNull(gprsNetworkRegistrationResponse.getCellId());
  }

  @Test
  public void test_unsolicited_5_with_lac_and_cellid() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 5,\"9B7D\",\"09CD\"");

    assertNull(gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9b7d), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09cd), gprsNetworkRegistrationResponse.getCellId());
  }

  @Test
  public void test_2_5_with_lac_and_cellid() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 2,5,\"ABCD\",\"123A\"");

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0xabcd), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x123a), gprsNetworkRegistrationResponse.getCellId());
  }

  @Test
  public void test_2_1_with_lac_and_cellid_and_act_and_rac() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 2,1,\"00DE\",\"2D3C1C6\",2,\"03\"");

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x2d3c1c6), gprsNetworkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationResponse.getAccessTechnology());
    assertEquals(new Integer(0x03), gprsNetworkRegistrationResponse.getRac());
  }

  @Test
  public void test2GWithLacAndCi() {
    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse("+CGREG: 1,\"00DE\",\"C1AD\",0,\"01\"");

    assertEquals(null, gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0xc1ad), gprsNetworkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationResponse.getAccessTechnology());
    assertEquals(new Integer(0x01), gprsNetworkRegistrationResponse.getRac());
  }
}