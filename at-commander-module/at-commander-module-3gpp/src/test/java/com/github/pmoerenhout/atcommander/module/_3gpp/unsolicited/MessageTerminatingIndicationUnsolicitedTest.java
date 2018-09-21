package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.MessageTerminatingIndicationUnsolicited;

public class MessageTerminatingIndicationUnsolicitedTest {

  @Test
  public void test_message_terminating_indication_response() throws Exception {
    final String line = "+CMTI: \"SM\",3";
    final MessageTerminatingIndicationUnsolicited messageTerminatingIndication = new MessageTerminatingIndicationUnsolicited();
    messageTerminatingIndication.parseUnsolicited(Collections.singletonList(line));
    assertEquals("SM", messageTerminatingIndication.getStorage());
    assertEquals(3, messageTerminatingIndication.getIndex());
  }

}