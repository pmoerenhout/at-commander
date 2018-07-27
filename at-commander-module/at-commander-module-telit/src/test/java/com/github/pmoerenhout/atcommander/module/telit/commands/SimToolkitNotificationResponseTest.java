package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

public class SimToolkitNotificationResponseTest {

  @Test
  public void test_stn_cmdtype() {
    final SimToolkitNotificationResponse simToolkitNotificationResponse = new SimToolkitNotificationResponse();
    simToolkitNotificationResponse.parseUnsolicited(Collections.singletonList("#STN: 237"));
    assertEquals(new Integer(237), simToolkitNotificationResponse.getCommandType());
    assertEquals(null, simToolkitNotificationResponse.getRefreshType());
  }

  @Test
  public void test_stn_cmdtype_and_refreshtype() {
    final SimToolkitNotificationResponse simToolkitNotificationResponse = new SimToolkitNotificationResponse();
    simToolkitNotificationResponse.parseUnsolicited(Collections.singletonList("#STN: 237,5"));
    assertEquals(new Integer(237), simToolkitNotificationResponse.getCommandType());
    assertEquals(new Integer(5), simToolkitNotificationResponse.getRefreshType());
  }
}