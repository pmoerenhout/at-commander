package org.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class ImsiResponseTest extends BaseCommandTest {
  @Test
  public void testImsi() throws Exception {
    final AtResponse response = createOkAtResponse("20404001234567");

    final ImsiResponse imsiResponse = new ImsiResponse(response);

    assertEquals("20404001234567", imsiResponse.getImsi());
  }
}