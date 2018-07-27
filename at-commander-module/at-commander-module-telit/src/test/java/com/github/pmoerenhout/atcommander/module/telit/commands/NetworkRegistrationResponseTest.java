package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationResponseTest extends BaseCommandTest {

  @Test
  public void test_creg_unsolicited_1() throws Exception {
    final String line = "+CREG: 1";

    final com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_creg_unsolicited_2() throws Exception {
    final String line = "+CREG: 2";

    final com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void test_creg_solicited_2_3() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,3");

    final com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse(response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.DENIED, networkRegistrationResponse.getRegistrationState());
  }

  @Test
  public void testCreg4WithLacAndCellId() throws Exception {
    final String line = "+CREG: 5,\"9BCD\",\"09AB\"";

    final com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9bcd), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09ab), networkRegistrationResponse.getCellId());
  }

  @Test
  public void test_creg_2_5_with_lac_and_cellid() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,5,\"9BCD\",\"09AB\"");

    final com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse = new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse(
        response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x9bcd), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x09ab), networkRegistrationResponse.getCellId());
  }

  @Test
  public void test_creg_with_lac_and_cellid_and_act() throws Exception {
    final String line = "+CREG: 1,\"00DE\",\"2D3C1B3\",2";

    final com.github.pmoerenhout.atcommander.module.telit.commands.NetworkRegistrationResponse networkRegistrationResponse =
        new com.github.pmoerenhout.atcommander.module.telit.commands.NetworkRegistrationResponse();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x2D3C1B3), networkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistrationResponse.getAccessTechnology());
  }

  @Test(expected = ParseException.class)
  public void test_creg_2_1_with_lac_and_with_cellid_and_act_not_parse_as_unsolicited() throws Exception {
    final String line = "+CREG: 2,1,\"00DE\",\"2D33F2C\",2";

    com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse networkRegistrationResponse = new com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));
  }

  @Test
  public void test_creg_2_1_with_lac_and_with_cellid_and_act() throws Exception {
    final AtResponse response = createOkAtResponse("+CREG: 2,1,\"00DE\",\"2D33F2C\",2");

    final com.github.pmoerenhout.atcommander.module.telit.commands.NetworkRegistrationResponse networkRegistrationResponse = new com.github.pmoerenhout.atcommander.module.telit.commands.NetworkRegistrationResponse(
        response);

    assertEquals(new Integer(2), networkRegistrationResponse.getMode());
    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistrationResponse.getRegistrationState());
    assertEquals(new Integer(0x00de), networkRegistrationResponse.getLac());
    assertEquals(new Integer(0x02d33f2c), networkRegistrationResponse.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistrationResponse.getAccessTechnology());
  }
}