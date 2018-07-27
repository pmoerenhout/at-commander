package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MessageTerminatingResponseTest {

  @Test
  public void test_cmt() {
    final List<String> lines = new ArrayList<>();
    lines.add("+CMT: \"\",22");
    lines.add("001122334455");

    final MessageTerminatingResponse messageTerminatingResponse = new MessageTerminatingResponse();
    messageTerminatingResponse.parseUnsolicited(lines);

    assertEquals("", messageTerminatingResponse.getAlpha());
    assertEquals(22, messageTerminatingResponse.getLength());
    assertEquals("001122334455", messageTerminatingResponse.getPdu());
  }
}