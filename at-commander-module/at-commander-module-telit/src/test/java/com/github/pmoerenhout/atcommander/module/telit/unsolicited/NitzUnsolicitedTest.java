package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class NitzUnsolicitedTest extends BaseCommandTest {

  @Test
  public void test_nitz_unsolicited_basic() throws Exception {
    final String line = "#NITZ: 03/12/31,16:07:54";

    assertPatternMatch(NitzUnsolicited.UNSOLICITED_PATTERN, line);

    final NitzUnsolicited nitz = new NitzUnsolicited();
    nitz.parseUnsolicited(Collections.singletonList(line));

    assertEquals(03, nitz.getYear());
    assertEquals(12, nitz.getMonth());
    assertEquals(31, nitz.getDay());
    assertEquals(16, nitz.getHour());
    assertEquals(7, nitz.getMinute());
    assertEquals(54, nitz.getSecond());
    assertNull(nitz.getTimezone());
    assertNull(nitz.getDst());

    assertEquals(LocalDateTime.of(2003, 12, 31, 16, 7, 54, 0), nitz.getDateTime());
  }

  @Test
  public void test_nitz_unsolicited_extended() throws Exception {
    final NitzUnsolicited nitz = new NitzUnsolicited();
    nitz.parseUnsolicited(Collections.singletonList("#NITZ: 99/12/31,16:07:54-08"));

    assertEquals(99, nitz.getYear());
    assertEquals(12, nitz.getMonth());
    assertEquals(31, nitz.getDay());
    assertEquals(16, nitz.getHour());
    assertEquals(7, nitz.getMinute());
    assertEquals(54, nitz.getSecond());
    assertEquals(Integer.valueOf(-8), nitz.getTimezone());
    assertNull(nitz.getDst());

    assertEquals(OffsetDateTime.of(1999, 12, 31, 16, 7, 54, 0, ZoneOffset.ofTotalSeconds(-8 * 900)), nitz.getDateTime());
  }

  @Test
  public void test_nitz_unsolicited_with_timezone() throws Exception {
    final NitzUnsolicited nitz = new NitzUnsolicited();
    nitz.parseUnsolicited(Collections.singletonList("#NITZ: 98/09/30,14:07:23-08,0"));

    assertEquals(98, nitz.getYear());
    assertEquals(9, nitz.getMonth());
    assertEquals(30, nitz.getDay());
    assertEquals(14, nitz.getHour());
    assertEquals(7, nitz.getMinute());
    assertEquals(23, nitz.getSecond());
    assertEquals(Integer.valueOf(-8), nitz.getTimezone());
    assertEquals(Integer.valueOf(0), nitz.getDst());

    assertEquals(OffsetDateTime.of(1998, 9, 30, 14, 7, 23, 0, ZoneOffset.ofTotalSeconds(-8 * 900)), nitz.getDateTime());
  }

  @Test
  public void test_nitz_unsolicited_with_timezone_and_dst() throws Exception {
    final NitzUnsolicited nitz = new NitzUnsolicited();
    nitz.parseUnsolicited(Collections.singletonList("#NITZ: 17/12/31,16:07:54+08,1"));

    assertEquals(17, nitz.getYear());
    assertEquals(12, nitz.getMonth());
    assertEquals(31, nitz.getDay());
    assertEquals(16, nitz.getHour());
    assertEquals(7, nitz.getMinute());
    assertEquals(54, nitz.getSecond());
    assertEquals(Integer.valueOf(8), nitz.getTimezone());
    assertEquals(Integer.valueOf(1), nitz.getDst());

    assertEquals(OffsetDateTime.of(2017, 12, 31, 16, 7, 54, 0, ZoneOffset.ofTotalSeconds(12 * 900)), nitz.getDateTime());
  }

}