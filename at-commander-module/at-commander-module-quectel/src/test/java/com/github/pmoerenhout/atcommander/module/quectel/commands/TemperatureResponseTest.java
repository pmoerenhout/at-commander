package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class TemperatureResponseTest extends BaseCommandTest {

  @Test
  public void testTemperature() throws Exception {
    final AtResponse response = createOkAtResponse("+QTEMP: 47,44,43");

    final TemperatureResponse temperatureResponse = new TemperatureResponse(response);
    assertEquals(47, temperatureResponse.getPmicTemp());
    assertEquals(44, temperatureResponse.getXoTemp());
    assertEquals(43, temperatureResponse.getPaTemp());
  }

}