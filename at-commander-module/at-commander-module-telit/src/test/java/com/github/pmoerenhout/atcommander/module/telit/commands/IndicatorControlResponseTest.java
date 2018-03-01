package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class IndicatorControlResponseTest extends BaseCommandTest {

  @Test
  public void test_indicator_controle_response_1() throws Exception {
    final AtResponse response = createOkAtResponse("+CIND: 5,7,1,0,0,0,1,0,5");

    final IndicatorControlResponse indicatorControlResponse = new IndicatorControlResponse(response);

    assertEquals(5, indicatorControlResponse.getBatteryChargeLevel());
    assertEquals(7, indicatorControlResponse.getSignalQuality());
    assertEquals(1, indicatorControlResponse.getServiceAvailability());
  }

  @Test
  public void test_indicator_controle_response_2() throws Exception {
    final AtResponse response = createOkAtResponse("+CIND: 5,0,0,0,0,0,0,0,4");

    final IndicatorControlResponse indicatorControlResponse = new IndicatorControlResponse(response);

    assertEquals(5, indicatorControlResponse.getBatteryChargeLevel());
  }

}