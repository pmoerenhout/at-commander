package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class ExtendedErrorReportResponseTest extends BaseCommandTest {

  @Test
  public void test_extended_error_report() throws Exception {
    final AtResponse response = createOkAtResponse("+CEER: \"CC setup error\",258,\"Invalid parameters\"");

    final ExtendedErrorReportResponse extendedErrorReportResponse = new ExtendedErrorReportResponse(response);

    final List<String> report = extendedErrorReportResponse.getReport();
    assertEquals(1, report.size());
    assertEquals("\"CC setup error\",258,\"Invalid parameters\"", report.get(0));
  }
}