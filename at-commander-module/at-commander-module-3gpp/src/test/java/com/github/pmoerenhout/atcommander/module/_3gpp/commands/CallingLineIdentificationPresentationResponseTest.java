package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CallingLineIdentificationPresentationResponseTest {

  @Test
  public void test_clip_unsolicited_2() throws Exception {
    final String line = "+CLIP: \"+31348000000\",145";

    final CallingLineIdentificationPresentationResponse clipResponse = new CallingLineIdentificationPresentationResponse(line);

    assertEquals("+31348000000", clipResponse.getNumber());
    assertEquals(145, clipResponse.getType());
  }

  @Test
  public void test_clip_unsolicited_6() throws Exception {
    final String line = "+CLIP: \"+31348503413\",145,\"\",128,\"\",0";

    final CallingLineIdentificationPresentationResponse clipResponse = new CallingLineIdentificationPresentationResponse(line);

    assertEquals("+31348503413", clipResponse.getNumber());
    assertEquals(145, clipResponse.getType());
    assertEquals("", clipResponse.getSubAddress());
    assertEquals(new Integer(128), clipResponse.getSubAddressType());
    assertEquals("", clipResponse.getAlpha());
    assertEquals(new Integer(0), clipResponse.getCliValidity());
  }

}