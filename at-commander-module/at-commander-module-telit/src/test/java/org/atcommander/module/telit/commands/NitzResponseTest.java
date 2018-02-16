package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NitzResponseTest {

  @Test
  public void testNitz() throws Exception {
    final NitzResponse nitzResponse = new NitzResponse("#NITZ: 15/04/03,16:07:54+08,1");

    assertEquals(15, nitzResponse.getYear());
    assertEquals(4, nitzResponse.getMonth());
    assertEquals(3, nitzResponse.getDay());
    assertEquals(16, nitzResponse.getHour());
    assertEquals(7, nitzResponse.getMinute());
    assertEquals(54, nitzResponse.getSecond());
    assertEquals(8, nitzResponse.getTimezone());
    assertEquals(1, nitzResponse.getDst());

  }

  @Test
  public void testNitz2() throws Exception {
    final NitzResponse nitzResponse = new NitzResponse("#NITZ: 99/12/31,16:07:54-08,0");

    assertEquals(99, nitzResponse.getYear());
    assertEquals(12, nitzResponse.getMonth());
    assertEquals(31, nitzResponse.getDay());
    assertEquals(16, nitzResponse.getHour());
    assertEquals(7, nitzResponse.getMinute());
    assertEquals(54, nitzResponse.getSecond());
    assertEquals(-8, nitzResponse.getTimezone());
    assertEquals(0, nitzResponse.getDst());
  }

}