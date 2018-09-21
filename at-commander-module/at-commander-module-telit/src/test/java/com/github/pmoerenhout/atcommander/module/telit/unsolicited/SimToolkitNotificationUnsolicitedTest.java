package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

public class SimToolkitNotificationUnsolicitedTest {

  @Test
  public void test_stn_cmdtype() {
    final SimToolkitNotificationUnsolicited simToolkitNotification = new SimToolkitNotificationUnsolicited();
    simToolkitNotification.parseUnsolicited(Collections.singletonList("#STN: 237"));
    assertEquals(Integer.valueOf(237), simToolkitNotification.getCommandType());
    assertNull( simToolkitNotification.getRefreshType());
  }

  @Test
  public void test_stn_cmdtype_and_refreshtype() {
    final SimToolkitNotificationUnsolicited simToolkitNotification = new SimToolkitNotificationUnsolicited();
    simToolkitNotification.parseUnsolicited(Collections.singletonList("#STN: 237,5"));
    assertEquals(Integer.valueOf(237), simToolkitNotification.getCommandType());
    assertEquals(Integer.valueOf(5), simToolkitNotification.getRefreshType());
  }
}