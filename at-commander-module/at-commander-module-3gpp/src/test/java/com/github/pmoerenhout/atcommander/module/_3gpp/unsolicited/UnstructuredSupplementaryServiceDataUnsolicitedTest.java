package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class UnstructuredSupplementaryServiceDataUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_cusd_unsolicited_1() throws Exception {
    final String line = "+CUSD: 1";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(1), ussd.getResponse());
  }

  @Test
  public void test_cusd_unsolicited_2() throws Exception {
    final String line = "+CUSD: 2,\"Welcome\"";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(2), ussd.getResponse());
    assertEquals("Welcome", ussd.getUssdString());
  }

  @Test
  public void test_cusd_unsolicited_3() throws Exception {
    final String line = "+CUSD: 0,\"Beste klant, uw huidige tegoed is 0,1 euro. Uw extra bonustegoed is 0 euro. Groet, KPN\",15";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(0), ussd.getResponse());
    assertEquals("Beste klant, uw huidige tegoed is 0,1 euro. Uw extra bonustegoed is 0 euro. Groet, KPN", ussd.getUssdString());
    assertEquals(Integer.valueOf(15), ussd.getDcs());
  }
}