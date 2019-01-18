package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class ServiceReportingControlUnsolicitedTest extends BaseCommandTest {

  @Test
  public void test_service_reporting_control() {
    final String line = "+CR: REL ASYNC";

    assertPatternMatch(ServiceReportingControlUnsolicited.UNSOLICITED_PATTERN, line);

    final ServiceReportingControlUnsolicited serviceReportingControl = new ServiceReportingControlUnsolicited();
    serviceReportingControl.parseUnsolicited(Collections.singletonList(line));

    assertEquals("REL ASYNC", serviceReportingControl.getService());
  }
}