package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class OperatorSelectionResponseTest extends BaseCommandTest {

  @Test
  public void test_simple() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 0");

    final OperatorSelectionResponse operatorSelectionResponse = new OperatorSelectionResponse(response);

    assertEquals(0, operatorSelectionResponse.getMode());
  }

  @Test
  public void test_numeric_format() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 2,2,\"20404\"");

    final OperatorSelectionResponse operatorSelectionResponse = new OperatorSelectionResponse(response);

    assertEquals(2, operatorSelectionResponse.getMode());
    assertEquals(new Integer(2), operatorSelectionResponse.getFormat());
    assertEquals("20404", operatorSelectionResponse.getOper());
    assertNull(operatorSelectionResponse.getAct());
  }

  @Test
  public void testParse2() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 2,2,\"20404\",6");

    final OperatorSelectionResponse operatorSelectionResponse = new OperatorSelectionResponse(response);

    assertEquals(2, operatorSelectionResponse.getMode());
    assertEquals(new Integer(2), operatorSelectionResponse.getFormat());
    assertEquals("20404", operatorSelectionResponse.getOper());
    assertEquals(new Integer(6), operatorSelectionResponse.getAct());
  }

}