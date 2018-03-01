package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module.v250.enums.PinStatus;
import org.junit.Test;

public class PinResponseTest extends BaseCommandTest {

  @Test
  public void testSimPin() {
    final AtResponse response = createOkAtResponse("+CPIN: SIM PIN");

    final PinResponse pinResponse = new PinResponse(response);

    assertEquals(PinStatus.SIM_PIN, pinResponse.getStatus());
  }

}