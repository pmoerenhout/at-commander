package org.atcommander.module._3gpp.commands;

import org.junit.Test;

import junit.framework.TestCase;

public class GprsEventReportingResponseTest extends TestCase {

  @Test
  public void testCgev1() throws Exception {
    final String line = "+CGEV: ME DEACT IP";

    final GprsEventReportingResponse gprsEventReportingResponse = new GprsEventReportingResponse(line);

    assertEquals("ME DEACT IP", gprsEventReportingResponse.getEvent());
    assertNull(gprsEventReportingResponse.getAddress());
  }

  @Test
  public void testCgev3() throws Exception {
    String line = "+CGEV: ME DEACT IP, \"10.141.177.84\", 1";

    final GprsEventReportingResponse gprsEventReportingResponse = new GprsEventReportingResponse(line);

    assertEquals("ME DEACT IP", gprsEventReportingResponse.getEvent());
    assertEquals("10.141.177.84", gprsEventReportingResponse.getAddress());
    assertEquals(1, gprsEventReportingResponse.getCid().intValue());
  }

  @Test
  public void testCgevMeDetach() throws Exception {
    final String line = "+CGEV: ME DETACH";

    final GprsEventReportingResponse gprsEventReportingResponse = new GprsEventReportingResponse(line);

    assertEquals("ME DETACH", gprsEventReportingResponse.getEvent());
    assertEquals(null, gprsEventReportingResponse.getAddress());
    assertEquals(null, gprsEventReportingResponse.getCid());
  }
}