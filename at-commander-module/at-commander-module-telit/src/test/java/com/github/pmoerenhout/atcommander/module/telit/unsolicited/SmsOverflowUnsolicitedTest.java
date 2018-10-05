package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class SmsOverflowUnsolicitedTest {

  @Test
  public void test_sms_overflow() {
    final SmsOverflowUnsolicited smsOverflow = new SmsOverflowUnsolicited();
    smsOverflow.parseUnsolicited(Collections.singletonList("#SMOV: \"SM\""));

    assertEquals("SM", smsOverflow.getMemo());
  }
}