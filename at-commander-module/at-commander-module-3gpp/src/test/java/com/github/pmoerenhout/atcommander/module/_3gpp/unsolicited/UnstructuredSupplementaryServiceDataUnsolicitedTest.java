package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    assertFalse(ussd.getUssdString().isPresent());
    assertFalse(ussd.getDcs().isPresent());
  }

  @Test
  public void test_cusd_unsolicited_2() throws Exception {
    final String line = "+CUSD: 2,\"Welcome\"";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(2), ussd.getResponse());
    assertEquals("Welcome", ussd.getUssdString().get());
    assertFalse(ussd.getDcs().isPresent());
  }

  @Test
  public void test_cusd_unsolicited_3() throws Exception {
    final String line = "+CUSD: 0,\"Beste klant, uw huidige tegoed is 0,1 euro. Uw extra bonustegoed is 0 euro. Groet, KPN\",15";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(0), ussd.getResponse());
    assertEquals("Beste klant, uw huidige tegoed is 0,1 euro. Uw extra bonustegoed is 0 euro. Groet, KPN", ussd.getUssdString().get());
    assertEquals(Integer.valueOf(15), ussd.getDcs().get());
  }

  @Test
  public void test_cusd_unsolicited_with_0x0d_char() throws Exception {
    final String line = "+CUSD: 0,\"Beste klant, wij zijn op 12 februari gestopt met deze bundel. Kijk op kpn.com/bundels voor meer informatie. Groet, KPN.\r\",15";

    assertPatternMatch(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN, line);

    final UnstructuredSupplementaryServiceDataUnsolicited ussd = new UnstructuredSupplementaryServiceDataUnsolicited();
    ussd.parseUnsolicited(Collections.singletonList(line));

    assertEquals(Integer.valueOf(0), ussd.getResponse());
    assertEquals("Beste klant, wij zijn op 12 februari gestopt met deze bundel. Kijk op kpn.com/bundels voor meer informatie. Groet, KPN.\r",
        ussd.getUssdString().get());
    assertEquals(Integer.valueOf(15), ussd.getDcs().get());
  }
}