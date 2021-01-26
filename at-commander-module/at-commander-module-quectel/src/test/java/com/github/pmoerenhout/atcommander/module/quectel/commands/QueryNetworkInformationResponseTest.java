package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class QueryNetworkInformationResponseTest extends BaseCommandTest {

  @Test
  public void testQueryNetworkInformation() throws Exception {
    final AtResponse response = createOkAtResponse("+QNWINFO: \"FDD LTE\",\"20408\",\"LTE BAND 20\",1300");

    final QueryNetworkInformationResponse networkInformationResponse = new QueryNetworkInformationResponse(response);
    assertEquals("FDD LTE", networkInformationResponse.getAccessTechnology());
    assertEquals("20408", networkInformationResponse.getOperator());
    assertEquals("LTE BAND 20", networkInformationResponse.getBand());
    assertEquals(Integer.valueOf("1300"), networkInformationResponse.getChannel());
  }

}