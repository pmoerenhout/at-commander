package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class SignalQualityResponseTest extends BaseCommandTest {

  @Test
  public void testSignalQualityResponse() throws Exception {
    final AtResponse response = createOkAtResponse("+CSQ: 2,99");

    final SignalQualityResponse signalQualityResponse = new SignalQualityResponse(response);

    assertEquals(2, signalQualityResponse.getRssi());
    assertEquals(99, signalQualityResponse.getBer());
  }
}