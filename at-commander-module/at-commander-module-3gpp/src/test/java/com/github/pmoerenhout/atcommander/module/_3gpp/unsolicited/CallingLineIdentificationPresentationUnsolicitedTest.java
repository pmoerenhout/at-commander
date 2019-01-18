package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class CallingLineIdentificationPresentationUnsolicitedTest extends BaseCommandTest {

  @Test
  public void test_clip_unsolicited_2() throws Exception {
    final String line = "+CLIP: \"+31348000000\",145";

    assertPatternMatch(CallingLineIdentificationPresentationUnsolicited.UNSOLICITED_PATTERN, line);

    final CallingLineIdentificationPresentationUnsolicited clipUnsolicited = new CallingLineIdentificationPresentationUnsolicited();
    clipUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("+31348000000", clipUnsolicited.getNumber());
    assertEquals(145, clipUnsolicited.getType());
  }

  @Test
  public void test_clip_unsolicited_6() throws Exception {
    final String line = "+CLIP: \"+31348503413\",145,\"\",128,\"\",0";

    assertPatternMatch(CallingLineIdentificationPresentationUnsolicited.UNSOLICITED_PATTERN, line);

    final CallingLineIdentificationPresentationUnsolicited clipUnsolicited = new CallingLineIdentificationPresentationUnsolicited();
    clipUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("+31348503413", clipUnsolicited.getNumber());
    assertEquals(145, clipUnsolicited.getType());
    assertEquals("", clipUnsolicited.getSubAddress());
    assertEquals(Integer.valueOf(128), clipUnsolicited.getSubAddressType());
    assertEquals("", clipUnsolicited.getAlpha());
    assertEquals(Integer.valueOf(0), clipUnsolicited.getCliValidity());
  }

}