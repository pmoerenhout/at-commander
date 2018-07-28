package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.pmoerenhout.atcommander.AtCommander;

@RunWith(MockitoJUnitRunner.class)
public class GprsEventReportingCommandTest {

  @Mock
  private AtCommander atCommander;

  @Test
  public void test_gprs_event_reporting_command() throws Exception {
    final GprsEventReportingCommand command = new GprsEventReportingCommand(atCommander, 1);
    assertEquals(1, command.getMode());
    assertNull(command.getBfr());
  }

  @Test
  public void test_gprs_event_reporting_command_with_bfr() {
    final GprsEventReportingCommand command = new GprsEventReportingCommand(atCommander, 2, 1);
    assertEquals(2, command.getMode());
    assertEquals(new Integer(1), command.getBfr());
  }
}