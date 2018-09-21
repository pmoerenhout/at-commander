package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusUnsolicitedTest {

  @Test
  public void test_qss_1() {
    final QuerySimStatusUnsolicited querySimStatus = new QuerySimStatusUnsolicited();
    querySimStatus.parseUnsolicited(Collections.singletonList("#QSS: 1"));

    Assert.assertEquals(SimStatus.INSERTED, querySimStatus.getStatus());
  }

  @Test
  public void test_qss_2() {
    final QuerySimStatusUnsolicited querySimStatus = new QuerySimStatusUnsolicited();
    querySimStatus.parseUnsolicited(Collections.singletonList("#QSS: 2"));

    assertEquals(SimStatus.INSERTED_PIN_UNLOCKED, querySimStatus.getStatus());
  }

}