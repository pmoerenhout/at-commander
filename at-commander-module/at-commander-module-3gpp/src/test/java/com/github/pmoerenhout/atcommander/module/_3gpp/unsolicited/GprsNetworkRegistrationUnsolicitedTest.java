package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;


public class GprsNetworkRegistrationUnsolicitedTest extends BaseCommandTest {

  // +CGREG: <stat>
  // +CGREG: <stat>[,<[lac>,]<[ci>],[<AcT>],[<rac>]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,<cause_type>,<reject_cause>]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,,[,[<Active- Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,[<cause_type>],[<reject_c ause>][,[<Active-Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]]

  @Test
  public void test_gprs_network_registration_stat_home() {
    final String line = "+CGREG: 1";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertNull(gprsNetworkRegistrationUnsolicited.getLac());
    assertNull(gprsNetworkRegistrationUnsolicited.getCellId());
  }

  @Test
  public void test_gprs_network_registration_stat_searching() {
    final String line = "+CGREG: 2";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.SEARCHING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertNull(gprsNetworkRegistrationUnsolicited.getLac());
    assertNull(gprsNetworkRegistrationUnsolicited.getCellId());
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

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0xeb7d), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x09cd), gprsNetworkRegistrationUnsolicited.getCellId());
  }

//  @Test
//  public void test_2_5_with_lac_and_cellid() {
//    final String line = "+CGREG: 2,5,\"ABCD\",\"123A\"";
//
//    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
//    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));
//
//    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
//    assertEquals(Integer.valueOf(0xabcd), gprsNetworkRegistrationUnsolicited.getLac());
//    assertEquals(Integer.valueOf(0x123a), gprsNetworkRegistrationUnsolicited.getCellId());
//    assertNull(gprsNetworkRegistrationUnsolicited.getAccessTechnology());
//  }

  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1C6\",2";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x2d3c1c6), gprsNetworkRegistrationUnsolicited.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
  }

  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,1,33";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x2d3c1ae), gprsNetworkRegistrationUnsolicited.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
    assertEquals(Integer.valueOf(1), gprsNetworkRegistrationUnsolicited.getCauseType());
    assertEquals(Integer.valueOf(33), gprsNetworkRegistrationUnsolicited.getRejectCause());
  }

  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause_3() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,,,01010101,10010110,11001100";

    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
    assertEquals(Integer.valueOf(0x2d3c1ae), gprsNetworkRegistrationUnsolicited.getCellId());
    assertEquals(AccessTechnology.UTRAN, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
    assertNull(gprsNetworkRegistrationUnsolicited.getCauseType());
    assertNull(gprsNetworkRegistrationUnsolicited.getRejectCause());
    assertEquals("01010101", gprsNetworkRegistrationUnsolicited.getActiveTime());
    assertEquals("10010110", gprsNetworkRegistrationUnsolicited.getPeriodicRau());
    assertEquals("11001100", gprsNetworkRegistrationUnsolicited.getGprsReadyTimer());
  }

  @Test
  public void test_gprs_network_registration_stat_home_with_lac_and_cellid_and_act_and_cause_2() {
    final String line = "+CGREG: 1,\"00DE\",\"2D3C1AE\",2,1,33,01010101,10010110,11001100";

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

//  @Test
//  public void test2GWithLacAndCi() {
//    final String line = "+CGREG: 1,\"00DE\",\"C1AD\"";
//
//    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
//    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));
//
//    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
//    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
//    assertEquals(Integer.valueOf(0xc1ad), gprsNetworkRegistrationUnsolicited.getCellId());
//    assertNull(gprsNetworkRegistrationUnsolicited.getAccessTechnology());
//  }

//  @Test
//  public void test_solicited_2G_withLacAndCiAndAct() {
//    final String line = "+CGREG: 2,1,\"00DE\",\"FD4F\",0";
//
//    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
//    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));
//
//    assertEquals(Integer.valueOf(2), gprsNetworkRegistrationUnsolicited.getMode());
//    assertEquals(RegistrationState.REGISTERED_HOME_NETWORK, gprsNetworkRegistrationUnsolicited.getRegistrationState());
//    assertEquals(Integer.valueOf(0x00de), gprsNetworkRegistrationUnsolicited.getLac());
//    assertEquals(Integer.valueOf(0xfd4f), gprsNetworkRegistrationUnsolicited.getCellId());
//    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
//  }

//  @Test
//  public void test_solicited_3G_withLacAndCiAndAct() {
//    final String line = "+CGREG: 2,5,\"0CBC\",\"D1A7\",0";
//
//    final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = new GprsNetworkRegistrationUnsolicited();
//    gprsNetworkRegistrationUnsolicited.parseUnsolicited(Collections.singletonList(line));
//
//    assertEquals(Integer.valueOf(2), gprsNetworkRegistrationUnsolicited.getMode());
//    assertEquals(RegistrationState.REGISTERED_ROAMING, gprsNetworkRegistrationUnsolicited.getRegistrationState());
//    assertEquals(Integer.valueOf(0x0cbc), gprsNetworkRegistrationUnsolicited.getLac());
//    assertEquals( Integer.valueOf(0xd1a7), gprsNetworkRegistrationUnsolicited.getCellId());
//    assertEquals(AccessTechnology.GSM, gprsNetworkRegistrationUnsolicited.getAccessTechnology());
//  }
}