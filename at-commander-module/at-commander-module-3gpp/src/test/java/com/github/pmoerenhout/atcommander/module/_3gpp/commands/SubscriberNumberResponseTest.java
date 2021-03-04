package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class SubscriberNumberResponseTest extends BaseCommandTest {

  @Test
  public void test_subscriber_number_response() throws Exception {
    final AtResponse response = createOkAtResponse("+CNUM: ,\"+31682346962\",145");

    final SubscriberNumberResponse subscriberNumberResponse = new SubscriberNumberResponse(response);

    assertNull(subscriberNumberResponse.getAlpha());
    assertEquals("+31682346962", subscriberNumberResponse.getNumber());
    assertEquals(145, subscriberNumberResponse.getType());
  }
}