package com.github.pmoerenhout.atcommander.module.telit.commands;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusResponseTest extends BaseCommandTest {

  @Test
  public void test_qss_solicited() {
    final AtResponse response = createOkAtResponse("#QSS: 2,3");

    final QuerySimStatusResponse querySimStatusResponse = new QuerySimStatusResponse(response);

    assertEquals(Integer.valueOf(2), querySimStatusResponse.getMode());
    assertEquals(SimStatus.INSERTED_READY, querySimStatusResponse.getStatus());
  }

}