package org.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module.v250.enums.PinStatus;
import org.junit.Test;

public class PinResponseTest extends BaseCommandTest {

  @Test
  public void testSimPin() {
    final AtResponse response = createOkAtResponse("+CPIN: SIM PIN");

    final PinResponse pinResponse = new PinResponse(response);

    assertEquals(PinStatus.SIM_PIN, pinResponse.getStatus());
  }

}