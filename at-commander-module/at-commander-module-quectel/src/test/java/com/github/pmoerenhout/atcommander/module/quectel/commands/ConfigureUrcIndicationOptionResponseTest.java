package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class ConfigureUrcIndicationOptionResponseTest extends BaseCommandTest {

  @Test
  public void testConfigureUrcIndicationOptionResponse() throws Exception {
    final AtResponse response = createOkAtResponse("+QURCCFG: \"urcport\",\"usbmodem\"");

    final ConfigureUrcIndicationOptionResponse configureUrcIndicationOptionResponse = new ConfigureUrcIndicationOptionResponse(response);
    assertEquals("usbmodem", configureUrcIndicationOptionResponse.getUrcport());
  }
}