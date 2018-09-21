package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class ClockResponseTest extends BaseCommandTest {

  @Test
  public void test_clock_response() throws Exception {
    final AtResponse response = createOkAtResponse("+CCLK: \"18/07/28,22:27:00+08\"");

    final ClockResponse clockResponse = new ClockResponse(response);

    assertEquals("18/07/28,22:27:00+08", clockResponse.getTime());
    assertEquals(OffsetDateTime.of(2018, 7, 28, 22, 27, 0, 0, ZoneOffset.ofTotalSeconds(8 * 900)), clockResponse.getDateTime());
  }

  @Test
  public void test_clock_response_with_minus_offset() throws Exception {
    final AtResponse response = createOkAtResponse("+CCLK: \"18/07/28,22:27:00-02\"");

    final ClockResponse clockResponse = new ClockResponse(response);

    assertEquals("18/07/28,22:27:00-02", clockResponse.getTime());
    assertEquals(OffsetDateTime.of(2018, 7, 28, 22, 27, 0, 0, ZoneOffset.ofTotalSeconds(-2 * 900)), clockResponse.getDateTime());
  }

  @Test
  public void test_clock_response_without_zone() throws Exception {
    final AtResponse response = createOkAtResponse("+CCLK: \"18/07/28,22:27:00\"");

    final ClockResponse clockResponse = new ClockResponse(response);

    assertEquals("18/07/28,22:27:00", clockResponse.getTime());
    assertEquals(LocalDateTime.of(2018, 7, 28, 22, 27, 0, 0), clockResponse.getDateTime());
  }

}