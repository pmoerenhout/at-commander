package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class ImsiResponseTest extends BaseCommandTest {
  @Test
  public void testImsi() throws Exception {
    final AtResponse response = createOkAtResponse("20404001234567");

    final ImsiResponse imsiResponse = new ImsiResponse(response);

    assertEquals("20404001234567", imsiResponse.getImsi());
  }
}