package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class InitializationStatusResponseTest extends BaseCommandTest {

  @Test
  public void testInitializationState() throws Exception {
    final AtResponse response = createOkAtResponse("+QINISTAT: 3");

    final InitializationStatusResponse iniStatResponse = new InitializationStatusResponse(response);
    assertEquals(3, iniStatResponse.getStatus());
  }
}