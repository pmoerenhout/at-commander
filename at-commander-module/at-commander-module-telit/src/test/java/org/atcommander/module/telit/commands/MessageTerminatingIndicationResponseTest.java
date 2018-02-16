package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class MessageTerminatingIndicationResponseTest extends BaseCommandTest {

  @Test
  public void testMessageTerminatingIndicationResponse() throws Exception {
    final AtResponse response = createOkAtResponse("+CMTI: \"SM\",3");

    final MessageTerminatingIndicationResponse messageTerminatingIndicationResponse = new MessageTerminatingIndicationResponse(response);

    assertEquals("SM", messageTerminatingIndicationResponse.getStorage());
    assertEquals(3, messageTerminatingIndicationResponse.getIndex());
  }
}