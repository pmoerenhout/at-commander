package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.Collections;

import org.junit.Test;

import junit.framework.TestCase;

public class GprsEventReportingUnsolicitedTest extends TestCase {

  @Test
  public void test_cgev_me_deact_ip() throws Exception {
    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList("+CGEV: ME DEACT IP"));

    assertEquals("ME DEACT IP", gprsEventReporting.getEvent());
    assertNull(gprsEventReporting.getAddress());
    assertNull(gprsEventReporting.getCid());
  }

  @Test
  public void test_cgev_me_deact_ip_with_ip() throws Exception {
    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList("+CGEV: ME DEACT IP, \"10.141.177.84\", 1"));

    assertEquals("ME DEACT IP", gprsEventReporting.getEvent());
    assertEquals("10.141.177.84", gprsEventReporting.getAddress());
    assertEquals(Integer.valueOf(1), gprsEventReporting.getCid());
  }

  @Test
  public void test_cgev_me_detach() throws Exception {
    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList("+CGEV: ME DETACH"));

    assertEquals("ME DETACH", gprsEventReporting.getEvent());
    assertNull(gprsEventReporting.getAddress());
    assertNull(gprsEventReporting.getCid());
  }
}