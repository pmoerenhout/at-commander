package com.github.pmoerenhout.atcommander;


import org.junit.Assert;
import org.junit.Test;

public class FinalResponseTest {

  @Test
  public void testOK() {
    final String line = "OK";

    final FinalResponseCode finalResponseCode = FinalResponseCode.OK;
    final FinalResponse finalResponse = finalResponseCode.fromStringEx(line);

    Assert.assertEquals(getFinalResponse(finalResponseCode), finalResponse);
  }

  @Test
  public void testMoreData() {
    final String line = "> ";

    final FinalResponseCode finalResponseCode = FinalResponseCode.MORE_DATA;
    final FinalResponse finalResponse = finalResponseCode.fromStringEx(line);

    Assert.assertEquals(getFinalResponse(finalResponseCode).getCode(), finalResponse.getCode());
  }

  private FinalResponse getFinalResponse(final FinalResponseCode finalResponseCode) {
    FinalResponse finalResponseDto = new FinalResponse(finalResponseCode, null);
    return finalResponseDto;
  }
}