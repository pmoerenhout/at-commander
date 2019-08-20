package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class GprsEventReportingUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_cgev_me_deact_ip() throws Exception {
    final String line = "+CGEV: ME DEACT IP";

    assertPatternMatch(GprsEventReportingUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList(line));

    assertEquals("ME DEACT IP", gprsEventReporting.getEvent());
    assertNull(gprsEventReporting.getAddress());
    assertNull(gprsEventReporting.getCid());
  }

  @Test
  public void test_cgev_me_deact_ip_with_ip() throws Exception {
    final String line = "+CGEV: ME DEACT IP, \"10.141.177.84\", 1";

    assertPatternMatch(GprsEventReportingUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList(line));

    assertEquals("ME DEACT IP", gprsEventReporting.getEvent());
    assertEquals("10.141.177.84", gprsEventReporting.getAddress());
    assertEquals(Integer.valueOf(1), gprsEventReporting.getCid());
  }

  @Test
  public void test_cgev_me_detach() throws Exception {
    final String line = "+CGEV: ME DETACH";

    assertPatternMatch(GprsEventReportingUnsolicited.UNSOLICITED_PATTERN, line);

    final GprsEventReportingUnsolicited gprsEventReporting = new GprsEventReportingUnsolicited();
    gprsEventReporting.parseUnsolicited(Collections.singletonList(line));

    assertEquals("ME DETACH", gprsEventReporting.getEvent());
    assertNull(gprsEventReporting.getAddress());
    assertNull(gprsEventReporting.getCid());
  }
}