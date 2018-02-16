package org.atcommander.module.v250.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.atcommander.AtResponse;
import org.atcommander.module.v250.V250FinalFactory;
import org.atcommander.module.v250.types.DialStatus;
import org.junit.Test;


public class DialResponseTest {

  @Test()
  public void test_no_carrier() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("NO CARRIER"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.NO_CARRIER, dialResponse.getStatus());
  }

  @Test()
  public void test_busy() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("BUSY"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.BUSY, dialResponse.getStatus());
  }

  @Test()
  public void test_connect() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("CONNECT"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.CONNECT, dialResponse.getStatus());
  }

  @Test()
  public void test_connect_with_text() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("CONNECT SOME TEXT"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.CONNECT, dialResponse.getStatus());
    assertEquals("SOME TEXT", dialResponse.getText());
  }

  @Test()
  public void test_error() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("ERROR"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.ERROR, dialResponse.getStatus());
  }

  @Test()
  public void test_no_dialtone() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("NO DIALTONE"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.NO_DIALTONE, dialResponse.getStatus());
  }
}