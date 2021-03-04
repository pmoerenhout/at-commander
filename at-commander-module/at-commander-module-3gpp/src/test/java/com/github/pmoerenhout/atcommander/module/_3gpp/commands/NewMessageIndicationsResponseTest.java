package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class NewMessageIndicationsResponseTest extends BaseCommandTest {

  @Test
  public void test_new_message_indications() throws Exception {
    final AtResponse response = createOkAtResponse("+CNMI: 2,1,3,4,5");

    final NewMessageIndicationsResponse newMessageIndicationsResponse = new NewMessageIndicationsResponse(response);

    assertEquals(2, newMessageIndicationsResponse.getMode());
    assertEquals(1, newMessageIndicationsResponse.getMt());
    assertEquals(3, newMessageIndicationsResponse.getBm());
    assertEquals(4, newMessageIndicationsResponse.getDs());
    assertEquals(5, newMessageIndicationsResponse.getBfr());
  }
}