package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_qss_1() {
    final String line = "#QSS: 1";

    assertPatternMatch(QuerySimStatusUnsolicited.UNSOLICITED_PATTERN, line);

    final QuerySimStatusUnsolicited querySimStatus = new QuerySimStatusUnsolicited();
    querySimStatus.parseUnsolicited(Collections.singletonList(line));

    Assert.assertEquals(SimStatus.INSERTED, querySimStatus.getStatus());
  }

  @Test
  public void test_qss_2() {
    final String line = "#QSS: 2";

    assertPatternMatch(QuerySimStatusUnsolicited.UNSOLICITED_PATTERN, line);

    final QuerySimStatusUnsolicited querySimStatus = new QuerySimStatusUnsolicited();
    querySimStatus.parseUnsolicited(Collections.singletonList(line));

    assertEquals(SimStatus.INSERTED_PIN_UNLOCKED, querySimStatus.getStatus());
  }

}