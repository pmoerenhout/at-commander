package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class OperatorSelectionResponseTest extends BaseCommandTest {

  @Test
  public void test_operator_selection_simple() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 0");

    final OperatorSelectionResponse operatorSelection = new OperatorSelectionResponse();
    operatorSelection.parseSolicited(response);

    assertEquals(0, operatorSelection.getMode());
    assertNull( operatorSelection.getFormat());
    assertNull( operatorSelection.getAct());
    assertNull( operatorSelection.getOper());
  }

  @Test
  public void test_operator_selection_numeric_format() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 2,2,\"20404\"");

    final OperatorSelectionResponse operatorSelection = new OperatorSelectionResponse();
    operatorSelection.parseSolicited(response);

    assertEquals(2, operatorSelection.getMode());
    assertEquals(Integer.valueOf(2), operatorSelection.getFormat());
    assertEquals("20404", operatorSelection.getOper());
    assertNull(operatorSelection.getAct());
  }

  @Test
  public void test_operator_selection_with_act() throws Exception {
    final AtResponse response = createOkAtResponse("+COPS: 2,2,\"20404\",6");

    final OperatorSelectionResponse operatorSelectionResponse = new OperatorSelectionResponse(response);

    assertEquals(2, operatorSelectionResponse.getMode());
    assertEquals(Integer.valueOf(2), operatorSelectionResponse.getFormat());
    assertEquals("20404", operatorSelectionResponse.getOper());
    assertEquals(Integer.valueOf(6), operatorSelectionResponse.getAct());
  }

}