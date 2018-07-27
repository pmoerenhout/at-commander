package com.github.pmoerenhout.atcommander.module.telit.commands;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusResponseTest extends BaseCommandTest {

  @Test
  public void testQss1() {
    final String line = "#QSS: 1";

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse();
    querySimStatusResponse.parseUnsolicited(Collections.singletonList(line));

    Assert.assertEquals(SimStatus.INSERTED, querySimStatusResponse.getStatus());
  }

  @Test
  public void testQssUnsolicited() {
    final String line = "#QSS: 2";

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse();
    querySimStatusResponse.parseUnsolicited(Collections.singletonList(line));

    assertNull(querySimStatusResponse.getMode());
    assertEquals(SimStatus.INSERTED_PIN_UNLOCKED, querySimStatusResponse.getStatus());
  }

  @Test
  public void testQssSolicited() {
    final AtResponse response = createOkAtResponse("#QSS: 2,3");

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse(response);

    assertEquals(new Integer(2), querySimStatusResponse.getMode());
    assertEquals(SimStatus.INSERTED_READY, querySimStatusResponse.getStatus());
  }

}