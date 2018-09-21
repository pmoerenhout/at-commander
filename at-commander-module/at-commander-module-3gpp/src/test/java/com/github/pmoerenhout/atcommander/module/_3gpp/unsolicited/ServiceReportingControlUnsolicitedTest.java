package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.ServiceReportingControlUnsolicited;

public class ServiceReportingControlUnsolicitedTest {

  @Test
  public void test_service_reporting_control() {
    final String line = "+CR: REL ASYNC";
    final ServiceReportingControlUnsolicited serviceReportingControl = new ServiceReportingControlUnsolicited();
    serviceReportingControl.parseUnsolicited(Collections.singletonList(line));

    assertEquals("REL ASYNC", serviceReportingControl.getService());
  }
}