package com.github.pmoerenhout.atcommander.module.v250.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ErrorException;
import com.github.pmoerenhout.atcommander.module.v250.V250FinalFactory;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.BusyException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.NoCarrierException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.NoDialToneException;
import com.github.pmoerenhout.atcommander.module.v250.types.DialStatus;
import org.junit.Test;


public class DialResponseTest {

  @Test(expected = NoCarrierException.class)
  public void test_no_carrier() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("NO CARRIER"));

    new DialResponse(response);
  }

  @Test(expected = BusyException.class)
  public void test_busy() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("BUSY"));

    new DialResponse(response);
  }

  @Test()
  public void test_connect() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("CONNECT"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.CONNECT, dialResponse.getStatus());
    assertNull(dialResponse.getText());
  }

  @Test()
  public void test_connect_with_text() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("CONNECT SOME TEXT"));

    final DialResponse dialResponse = new DialResponse(response);

    assertEquals(DialStatus.CONNECT, dialResponse.getStatus());
    assertEquals("SOME TEXT", dialResponse.getText());
  }

  @Test(expected = ErrorException.class)
  public void test_error() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("ERROR"));

    new DialResponse(response);
  }

  @Test(expected = NoDialToneException.class)
  public void test_no_dialtone() throws Exception {
    final AtResponse response = new AtResponse(Collections.singletonList(new V250FinalFactory()), Collections.singletonList("NO DIALTONE"));

    new DialResponse(response);
  }
}