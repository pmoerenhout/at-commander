package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.basic.exceptions.ParseException;
import org.atcommander.module._3gpp.RegistrationState;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.junit.Test;

public class NetworkRegistrationResponseTest extends BaseCommandTest {

  @Test
  public void test_creg_unsolicited_1() throws Exception {
    final String line = "+CREG: 1";

    final org.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse = new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(
        line);

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_creg_unsolicited_2() throws Exception {
    final String line = "+CREG: 2";

    final org.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(line);

    assertEquals(RegistrationState.SEARCHING, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_creg_solicited_2_3() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,3");

    final org.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.DENIED, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void testCreg4WithLacAndCellId() throws Exception {
    final String line = "+CREG: 5,\"9BCD\",\"09AB\"";

    final org.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(line);

    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9bcd), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09ab), networkRegistrationResponse.getCellId());
  }

  @Test
  public void test_creg_2_5_with_lac_and_cellid() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,5,\"9BCD\",\"09AB\"");

    final org.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse = new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(
        response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9bcd), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09ab), networkRegistrationResponse.getCellId());
  }

  @Test
  public void test_creg_with_lac_and_cellid_and_act() throws Exception {
    final String line = "+CREG: 1,\"00DE\",\"2D3C1B3\",2";

    final org.atcommander.module.telit.commands.NetworkRegistrationResponse networkRegistrationResponse = new org.atcommander.module.telit.commands.NetworkRegistrationResponse(
        line);

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x2D3C1B3), networkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistrationResponse.getAccessTechnology());
  }

  @Test(expected = ParseException.class)
  public void test_creg_2_1_with_lac_and_with_cellid_and_act_not_parse_as_unsolicited() throws Exception {
    final String line = "+CREG: 2,1,\"00DE\",\"2D33F2C\",2";

    new org.atcommander.module._3gpp.commands.NetworkRegistrationResponse(line);
  }

  @Test
  public void test_creg_2_1_with_lac_and_with_cellid_and_act() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,1,\"00DE\",\"2D33F2C\",2");

    final org.atcommander.module.telit.commands.NetworkRegistrationResponse networkRegistrationResponse = new org.atcommander.module.telit.commands.NetworkRegistrationResponse(response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x02d33f2c), networkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistrationResponse.getAccessTechnology());
  }
}