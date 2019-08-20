package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class SmsOverflowUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_sms_overflow() {
    final String line = "#SMOV: \"SM\"";

    assertPatternMatch(SmsOverflowUnsolicited.UNSOLICITED_PATTERN, line);

    final SmsOverflowUnsolicited smsOverflow = new SmsOverflowUnsolicited();
    smsOverflow.parseUnsolicited(Collections.singletonList(line));

    assertEquals("SM", smsOverflow.getMemo());
  }
}