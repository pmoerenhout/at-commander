package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class QuectelPingResponseTest extends BaseCommandTest {

  @Test
  public void testPing() throws Exception {
    final String line = "+QPING: 0,\"1.2.3.4\",128,300,76";

    assertPatternMatch(QuectelPingUnsolicited.UNSOLICITED_PATTERN, line);

    final QuectelPingUnsolicited quectelPingUnsolicited = new QuectelPingUnsolicited();
    quectelPingUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(0, quectelPingUnsolicited.getResult());
    assertEquals("1.2.3.4", quectelPingUnsolicited.getIpAddress());
    assertEquals(128, quectelPingUnsolicited.getBytes());
    assertEquals(300, quectelPingUnsolicited.getTime());
    assertEquals(76, quectelPingUnsolicited.getTtl());
  }

}