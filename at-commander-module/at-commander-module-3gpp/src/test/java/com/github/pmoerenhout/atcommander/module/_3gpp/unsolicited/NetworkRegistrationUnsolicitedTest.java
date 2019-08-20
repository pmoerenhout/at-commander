package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_creg_unsolicited_1() throws Exception {
    final String line = "+CREG: 1";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistration.getRegistrationState());
  }

  @Test
  public void test_creg_unsolicited_2() throws Exception {
    final String line = "+CREG: 2";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, networkRegistration.getRegistrationState());
  }

  @Test
  public void test_creg_unsolicited_creg_1_with_lac_and_with_cellid() throws Exception {
    final String line = "+CREG: 1,\"00DE\",\"2E2EE99\"";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, networkRegistration.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), networkRegistration.getLac());
    assertEquals(Integer.valueOf(0x2e2ee99), networkRegistration.getCellId());
    assertNull(networkRegistration.getAccessTechnology());
    assertNull(networkRegistration.getCauseType());
    assertNull(networkRegistration.getRejectCause());
  }

  @Test
  public void test_unsolicited_creg_2_with_lac_and_with_cellid_and_with_act() throws Exception {
    final String line = "+CREG: 2,\"FFFF\",\"F2D33F2C\",2";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, networkRegistration.getRegistrationState());
    assertEquals(Integer.valueOf(0xffff), networkRegistration.getLac());
    assertEquals(Integer.valueOf(0xf2d33f2c), networkRegistration.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistration.getAccessTechnology());
    assertNull(networkRegistration.getCauseType());
    assertNull(networkRegistration.getRejectCause());
  }

  @Test
  public void test_unsolicited_creg_2_with_lac_and_with_cellid_and_with_act_and_with_cause_type() throws Exception {
    final String line = "+CREG: 2,\"FFFA\",\"F2D33F2D\",2,0";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, networkRegistration.getRegistrationState());
    assertEquals(Integer.valueOf(0xfffa), networkRegistration.getLac());
    assertEquals(Integer.valueOf(0xf2d33f2d), networkRegistration.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistration.getAccessTechnology());
    assertEquals(Integer.valueOf(0), networkRegistration.getCauseType());
    assertNull(networkRegistration.getRejectCause());
  }

  @Test
  public void test_unsolicited_creg_2_with_lac_and_with_cellid_and_with_act_and_with_cause_type_and_reject_cause() throws Exception {
    final String line = "+CREG: 2,\"FFFF\",\"F2D33F2C\",2,1,44";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, networkRegistration.getRegistrationState());
    assertEquals(Integer.valueOf(0xffff), networkRegistration.getLac());
    assertEquals(Integer.valueOf(0xf2d33f2c), networkRegistration.getCellId());
    assertEquals(AccessTechnology.UTRAN, networkRegistration.getAccessTechnology());
    assertEquals(Integer.valueOf(1), networkRegistration.getCauseType());
    assertEquals(Integer.valueOf(44), networkRegistration.getRejectCause());
  }

  @Test
  public void testCreg4WithLacAndCellId() throws Exception {
    final String line = "+CREG: 5,\"9BCD\",\"09AB\"";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final NetworkRegistrationUnsolicited networkRegistration = new NetworkRegistrationUnsolicited();
    networkRegistration.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, networkRegistration.getRegistrationState());
    assertEquals(Integer.valueOf(0x9bcd), networkRegistration.getLac());
    assertEquals(Integer.valueOf(0x09ab), networkRegistration.getCellId());
  }

  @Test(expected = AssertionError.class)
  public void test_creg_2_1_with_lac_and_with_cellid_and_act_not_parse_as_unsolicited() throws Exception {
    final String line = "+CREG: 2,1,\"00DE\",\"2E2EE99\",2";

    assertPatternMatch(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);
  }

  @Test(expected = ParseException.class)
  public void test_creg_2_1_with_lac_and_with_cellid_and_act_not_parse_as_unsolicited_1() throws Exception {
    final String line = "+CREG: 2,1,\"00DE\",\"2E2EE99\",2";

    final NetworkRegistrationUnsolicited networkRegistrationResponse = new NetworkRegistrationUnsolicited();
    networkRegistrationResponse.parseUnsolicited(Collections.singletonList(line));
  }

}