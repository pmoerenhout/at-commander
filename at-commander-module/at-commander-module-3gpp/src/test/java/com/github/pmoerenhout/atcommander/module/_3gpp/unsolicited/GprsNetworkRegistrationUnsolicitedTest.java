package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;


public class GprsNetworkRegistrationUnsolicitedTest extends UnsolicitedTest {

  // +CGREG: <stat>
  // +CGREG: <stat>[,<[lac>,]<[ci>],[<AcT>],[<rac>]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,<cause_type>,<reject_cause>]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,,[,[<Active- Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,[<cause_type>],[<reject_c ause>][,[<Active-Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]]

  @Test
  public void test_gprs_network_registration_stat_home() {
    final String line = "+CGREG: 1";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertFalse(gprsNetworkRegistrationUnsolicited.getLac().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCellId().isPresent());
  }

  @Test(expected = AssertionError.class)
  public void test_gprs_network_registration_response_throws_exception() {
    final String line = "+CGREG: 2,0";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);
  }

  @Test
  public void test_gprs_network_registration_stat_searching() {
    final String line = "+CGREG: 2";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertFalse(gprsNetworkRegistrationUnsolicited.getLac().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCellId().isPresent());
  }

//  @Test
//  public void test_2_5() {
//    final String line = "+CGREG: 2,5";
//
//    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
//    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));
//
//    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
//    assertNull(gprsNetworkRegistrationUnsolicited.getLac());
//    assertNull(gprsNetworkRegistrationUnsolicited.getCellId());
//  }

  @Test
  public void test_gprs_network_registration_stat_roaming_with_lac_and_cellid() {
    final String line = "+CGREG: 5,\"EB7D\",\"09CD\"";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0xeb7d), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x09cd), gprsNetworkRegistrationUnsolicited.getCellId().get());
  }

  @Ignore
  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1C6\",2";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x2d3c1c6), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
  }

  @Ignore
  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,1,33";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x2d3c1ae), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology().get());
    assertEquals(Integer.valueOf(1), gprsNetworkRegistrationUnsolicited.getCauseType().get());
    assertEquals(Integer.valueOf(33), gprsNetworkRegistrationUnsolicited.getRejectCause().get());
  }

  @Ignore
  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause_2() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,,,01010101,10010110,11001100";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x2d3c1ae), gprsNetworkRegistrationUnsolicited.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
    assertNull(gprsNetworkRegistrationUnsolicited.getCauseType());
    assertNull(gprsNetworkRegistrationUnsolicited.getRejectCause());
    assertEquals("01010101", gprsNetworkRegistrationUnsolicited.getActiveTime().get());
    assertEquals("10010110", gprsNetworkRegistrationUnsolicited.getPeriodicRau().get());
    assertEquals("11001100", gprsNetworkRegistrationUnsolicited.getGprsReadyTimer().get());
  }

  @Ignore
  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause_3() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,1,33,01010101,10010110,11001100";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x2d3c1ae), gprsNetworkRegistrationUnsolicited.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
    assertEquals(Integer.valueOf(1), gprsNetworkRegistrationUnsolicited.getCauseType());
    assertEquals(Integer.valueOf(33), gprsNetworkRegistrationUnsolicited.getRejectCause());
    assertEquals("01010101", gprsNetworkRegistrationUnsolicited.getActiveTime());
    assertEquals("10010110", gprsNetworkRegistrationUnsolicited.getPeriodicRau());
    assertEquals("11001100", gprsNetworkRegistrationUnsolicited.getGprsReadyTimer());
  }

  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause_4() {
    final String line = "+CGREG: 1,\"00DE\",\"0A5B\",0,\"01\"";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x0a5b), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology().get());
    assertEquals(Byte.valueOf((byte) 0x01), gprsNetworkRegistrationUnsolicited.getRoutingAreaCode().get());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCauseType().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getRejectCause().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getActiveTime().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getPeriodicRau().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getGprsReadyTimer().isPresent());
  }

  @Test
  public void test_gprs_network_registration_stat_roaming_with_lac_and_cellid_and_act_and_cause() {
    final String line = "+CGREG: 5,\"00DE\",\"0A5B\",0,\"01\"";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x0a5b), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology().get());
    assertEquals(Byte.valueOf((byte) 0x01), gprsNetworkRegistrationUnsolicited.getRoutingAreaCode().get());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCauseType().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getRejectCause().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getActiveTime().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getPeriodicRau().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getGprsReadyTimer().isPresent());
  }

  @Test
  public void test_gprs_network_registration_stat_roaming_with_lac_and_cellid_and_act_and_cause_2() {
    final String line = "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0xfd4f), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology().get());
    assertEquals(Byte.valueOf((byte) 0x01), gprsNetworkRegistrationUnsolicited.getRoutingAreaCode().get());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCauseType().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getRejectCause().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getActiveTime().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getPeriodicRau().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getGprsReadyTimer().isPresent());
  }

  @Test
  public void test_gprs_network_registration_stat_roaming_with_lac_and_cellid_and_act_and_cause_3() {
    final String line = "+CGREG: 5,\"00DE\",\"0A5B\",0,\"01\"";

    assertPatternMatch(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac().get());
    assertEquals(Integer.valueOf(0x0a5b), gprsNetworkRegistrationUnsolicited.getCellId().get());
    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology().get());
    assertEquals(Byte.valueOf((byte) 0x01), gprsNetworkRegistrationUnsolicited.getRoutingAreaCode().get());
    assertFalse(gprsNetworkRegistrationUnsolicited.getCauseType().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getRejectCause().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getActiveTime().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getPeriodicRau().isPresent());
    assertFalse(gprsNetworkRegistrationUnsolicited.getGprsReadyTimer().isPresent());
  }
}