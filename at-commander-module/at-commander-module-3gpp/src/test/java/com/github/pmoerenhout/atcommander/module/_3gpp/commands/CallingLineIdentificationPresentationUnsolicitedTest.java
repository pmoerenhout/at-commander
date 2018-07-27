package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class CallingLineIdentificationPresentationUnsolicitedTest {

  @Test
  public void test_clip_unsolicited_2() throws Exception {
    final String line = "+CLIP: \"+31348000000\",145";
    final CallingLineIdentificationPresentationUnsolicited clipUnsolicited = new CallingLineIdentificationPresentationUnsolicited();
    clipUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("+31348000000", clipUnsolicited.getNumber());
    assertEquals(145, clipUnsolicited.getType());
  }

  @Test
  public void test_clip_unsolicited_6() throws Exception {
    final String line = "+CLIP: \"+31348503413\",145,\"\",128,\"\",0";
    final CallingLineIdentificationPresentationUnsolicited clipUnsolicited = new CallingLineIdentificationPresentationUnsolicited();
    clipUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("+31348503413", clipUnsolicited.getNumber());
    assertEquals(145, clipUnsolicited.getType());
    assertEquals("", clipUnsolicited.getSubAddress());
    assertEquals(new Integer(128), clipUnsolicited.getSubAddressType());
    assertEquals("", clipUnsolicited.getAlpha());
    assertEquals(new Integer(0), clipUnsolicited.getCliValidity());
  }

}