package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class SignalQualityResponseTest extends BaseCommandTest {

  @Test
  public void testGsm() throws Exception {
    final AtResponse response = createOkAtResponse("+QCSQ: \"GSM\",-51");

    final SignalQualityResponse qcsqResponse = new SignalQualityResponse(response);
    assertEquals("GSM", qcsqResponse.getSysmode());
    assertEquals(Integer.valueOf("-51"), qcsqResponse.getRssi());
  }

  @Test
  public void testCatM1() throws Exception {
    final AtResponse response = createOkAtResponse("+QCSQ: \"CAT-M1\",-52,-81,195,-10");

    final SignalQualityResponse qcsqResponse = new SignalQualityResponse(response);

    assertEquals("CAT-M1", qcsqResponse.getSysmode());
    assertEquals(Integer.valueOf("-52"), qcsqResponse.getRssi());
    assertEquals(Integer.valueOf("-81"), qcsqResponse.getRsrp());
    assertEquals(Integer.valueOf("195"), qcsqResponse.getSinr());
    assertEquals(Integer.valueOf("-10"), qcsqResponse.getRsrq());
  }
}