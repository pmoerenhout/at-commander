package org.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module._3gpp.RegistrationState;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.junit.Test;


public class GprsNetworkRegistrationResponseTest extends BaseCommandTest {

  // +CGREG: <n>,<stat>[,<lac>,<ci>[,<AcT>]

  @Test
  public void test_unsolicited_1() {
    final String line = "+CGREG: 1";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_unsolicited_2() {
    final String line = "+CGREG: 2";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

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
    final String line = "+CGREG: 5,\"9B7D\",\"09CD\"";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertNull(gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9b7d), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09cd), gprsNetworkRegistrationResponse.getCellId());
  }

  @Test
  public void test_2_5_with_lac_and_cellid() {
    final String line = "+CGREG: 2,5,\"ABCD\",\"123A\"";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0xabcd), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x123a), gprsNetworkRegistrationResponse.getCellId());
    assertNull(gprsNetworkRegistrationResponse.getAccessTechnology());
  }

  @Test
  public void test_2_1_with_lac_and_cellid_and_act() {
    final String line = "+CGREG: 2,1,\"00DE\",\"2D3C1C6\",2";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0x2d3c1c6), gprsNetworkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationResponse.getAccessTechnology());
  }

  @Test
  public void test2GWithLacAndCi() {
    final String line = "+CGREG: 1,\"00DE\",\"C1AD\"";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(null, gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0xc1ad), gprsNetworkRegistrationResponse.getCellId());
    assertNull(gprsNetworkRegistrationResponse.getAccessTechnology());
  }

  @Test
  public void test_solicited_2G_withLacAndCiAndAct() {
    final String line = "+CGREG: 2,1,\"00DE\",\"FD4F\",0";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0xfd4f), gprsNetworkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationResponse.getAccessTechnology());
  }

  @Test
  public void test_solicited_3G_withLacAndCiAndAct() {
    final String line = "+CGREG: 2,5,\"0CBC\",\"D1A7\",0";

    final GprsNetworkRegistrationResponse gprsNetworkRegistrationResponse = new GprsNetworkRegistrationResponse(line);

    assertEquals(new Integer(2), gprsNetworkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x0cbc), gprsNetworkRegistrationResponse.getLac());
    assertEquals(new Integer(0xd1a7), gprsNetworkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationResponse.getAccessTechnology());
  }
}