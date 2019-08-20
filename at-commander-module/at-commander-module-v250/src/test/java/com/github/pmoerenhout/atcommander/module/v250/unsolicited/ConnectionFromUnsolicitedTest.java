package com.github.pmoerenhout.atcommander.module.v250.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.unsolicited.UnsolicitedTest;

public class ConnectionFromUnsolicitedTest extends UnsolicitedTest {

  @Test
  public void test_connection_from() {
    final String line = "+CONN FROM: 31612345678";

    assertPatternMatch(ConnectionFromUnsolicited.UNSOLICITED_PATTERN, line);

    final ConnectionFromUnsolicited connectionFromUnsolicited = new ConnectionFromUnsolicited();
    connectionFromUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("31612345678", connectionFromUnsolicited.getAddress());
  }

}