package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.MessageTerminatingUnsolicited;

public class MessageTerminatingUnsolicitedTest {

  @Test
  public void test_cmt() {
    final List<String> lines = new ArrayList<>();
    lines.add("+CMT: \"\",22");
    lines.add("001122334455");

    final MessageTerminatingUnsolicited messageTerminatingUnsolicited = new MessageTerminatingUnsolicited();
    messageTerminatingUnsolicited.parseUnsolicited(lines);

    assertEquals("", messageTerminatingUnsolicited.getAlpha());
    assertEquals(22, messageTerminatingUnsolicited.getLength());
    assertEquals("001122334455", messageTerminatingUnsolicited.getPdu());
  }
}