package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class MessageTerminatingUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_cmt_pdu() {
    final List<String> lines = new ArrayList<>();
    lines.add("+CMT: \"\",22");
    lines.add("001122334455");

    assertPatternMatch(MessageTerminatingUnsolicited.UNSOLICITED_PATTERN, lines.get(0));

    final MessageTerminatingUnsolicited messageTerminatingUnsolicited = new MessageTerminatingUnsolicited();
    messageTerminatingUnsolicited.parseUnsolicited(lines);

    assertEquals("", messageTerminatingUnsolicited.getAlpha());
    assertEquals(22, messageTerminatingUnsolicited.getLength());
    assertEquals("001122334455", messageTerminatingUnsolicited.getPdu());
  }

  @Test
  public void test_cmt_pdu_no_quotes() {
    final List<String> lines = new ArrayList<>();
    lines.add("+CMT: ,51");
    lines.add("099193338548000011F6040D91137910248665F600000250708112750022CBA7B40882C1622010480693C15AB05A0B76A3CA603A594C57A3B96E3319");

    assertPatternMatch(MessageTerminatingUnsolicited.UNSOLICITED_PATTERN, lines.get(0));

    final MessageTerminatingUnsolicited messageTerminatingUnsolicited = new MessageTerminatingUnsolicited();
    messageTerminatingUnsolicited.parseUnsolicited(lines);

    assertNull(messageTerminatingUnsolicited.getAlpha());
    assertEquals(51, messageTerminatingUnsolicited.getLength());
    assertEquals("099193338548000011F6040D91137910248665F600000250708112750022CBA7B40882C1622010480693C15AB05A0B76A3CA603A594C57A3B96E3319",
        messageTerminatingUnsolicited.getPdu());
  }
}