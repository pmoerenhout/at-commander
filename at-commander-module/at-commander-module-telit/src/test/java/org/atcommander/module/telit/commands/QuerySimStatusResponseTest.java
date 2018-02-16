package org.atcommander.module.telit.commands;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module._3gpp.SimStatus;
import org.junit.Assert;
import org.junit.Test;

public class QuerySimStatusResponseTest extends BaseCommandTest {

  @Test
  public void testQss1() {
    final String line = "#QSS: 1";

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse(line);

    Assert.assertEquals(SimStatus.INSERTED, querySimStatusResponse.getStatus());
  }

  @Test
  public void testQssUnsolicited() {
    final String line = "#QSS: 2";

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse(line);

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