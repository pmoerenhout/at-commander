package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module.v250.enums.Status;

public class OperatorSelectionTestResponseTest extends BaseCommandTest {

  @Test
  public void test_cops_test() throws Exception {
    final AtResponse response = createOkAtResponse(
        "+COPS: (2,\"vodafone NL\",,\"20404\"),(1,\"T-Mobile NL\",,\"20416\"),(1,\"NL KPN\",,\"20408\"),,(0-4),(0,2)");

    final OperatorSelectionTestResponse operatorSelectionResponse = new OperatorSelectionTestResponse(response);

    assertEquals(3, operatorSelectionResponse.getOperators().size());

    assertEquals(Status.CURRENT, operatorSelectionResponse.getOperators().get(0).getStatus());
    assertEquals("vodafone NL", operatorSelectionResponse.getOperators().get(0).getOperatorLong());
    assertNull(operatorSelectionResponse.getOperators().get(0).getOperatorShort());
    assertEquals("20404", operatorSelectionResponse.getOperators().get(0).getOperatorNumeric());

    assertEquals(Status.AVAILABLE, operatorSelectionResponse.getOperators().get(1).getStatus());
    assertEquals("T-Mobile NL", operatorSelectionResponse.getOperators().get(1).getOperatorLong());
    assertNull(operatorSelectionResponse.getOperators().get(1).getOperatorShort());
    assertEquals("20416", operatorSelectionResponse.getOperators().get(1).getOperatorNumeric());

    assertEquals("0-4", operatorSelectionResponse.getMode());
    assertEquals("0,2", operatorSelectionResponse.getFormat());
  }

}