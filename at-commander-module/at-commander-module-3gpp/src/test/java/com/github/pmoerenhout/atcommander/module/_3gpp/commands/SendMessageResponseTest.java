package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class SendMessageResponseTest extends BaseCommandTest {

  @Test
  public void test_send_sms_response() {
    final AtResponse response = createOkAtResponse("+CMGS: 55");

    final SendMessageResponse sendMessageResponse = new SendMessageResponse(response);

    assertEquals(55, sendMessageResponse.getReference());
    assertNull(sendMessageResponse.getAckPdu());
  }

  @Test
  public void test_send_sms_response_with_ack_pdu() {
    final AtResponse response = createOkAtResponse("+CMGS: 56,234566");

    final SendMessageResponse sendMessageResponse = new SendMessageResponse(response);

    assertEquals(56, sendMessageResponse.getReference());
    assertEquals("234566", sendMessageResponse.getAckPdu());
  }
}