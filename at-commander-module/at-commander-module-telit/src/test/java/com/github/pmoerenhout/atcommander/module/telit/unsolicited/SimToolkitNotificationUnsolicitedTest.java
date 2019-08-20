package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class SimToolkitNotificationUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_stn_cmdtype() {
    final String line = "#STN: 237";

    assertPatternMatch(SimToolkitNotificationUnsolicited.UNSOLICITED_PATTERN, line);

    final SimToolkitNotificationUnsolicited simToolkitNotification = new SimToolkitNotificationUnsolicited();
    simToolkitNotification.parseUnsolicited(Collections.singletonList(line));
    assertEquals(Integer.valueOf(237), simToolkitNotification.getCommandType());
    assertNull(simToolkitNotification.getRefreshType());
  }

  @Test
  public void test_stn_cmdtype_and_refreshtype() {
    final String line = "#STN: 237,5";

    assertPatternMatch(SimToolkitNotificationUnsolicited.UNSOLICITED_PATTERN, line);

    final SimToolkitNotificationUnsolicited simToolkitNotification = new SimToolkitNotificationUnsolicited();
    simToolkitNotification.parseUnsolicited(Collections.singletonList(line));
    assertEquals(Integer.valueOf(237), simToolkitNotification.getCommandType());
    assertEquals(Integer.valueOf(5), simToolkitNotification.getRefreshType());
  }
}