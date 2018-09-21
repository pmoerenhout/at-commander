package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class SignalQualityResponseTest extends BaseCommandTest {

  @Test
  public void test_signal_quality_response() throws Exception {
    final AtResponse response = createOkAtResponse("+CSQ: 2,99");

    final SignalQualityResponse signalQuality = new SignalQualityResponse(response);

    assertEquals(2, signalQuality.getRssi());
    assertEquals(99, signalQuality.getBer());
  }
}