package com.github.pmoerenhout.atcommander;

import java.util.Collections;
import java.util.List;

public class AtResponse {

  private List<String> informationalText;
  private String finalResult;
  // private FinalResponseCode finalResponseCode;
  private AbstractFinalResponse abstractFinalResponse;

  public AtResponse(final List<FinalResponseFactory> finalFactories, final List<String> lines) {
    final int size = lines.size();
    this.informationalText = size > 1 ? lines.subList(0, size - 1) : Collections.EMPTY_LIST;
    // Last line contains the final status
    this.finalResult = lines.get(size - 1);
    for (final FinalResponseFactory finalResponseFactory : finalFactories) {
      final AbstractFinalResponse fi = finalResponseFactory.generate(finalResult);
      if (fi != null){
        this.abstractFinalResponse = fi;
        return;
      }
    }

    throw new IllegalStateException("AtResponse is NULL for '" + finalResult + "'");

//    this.finalResponseCode = FinalResponseCode.fromString(finalResult);
//    if (finalResponseCode == null) {
//      throw new IllegalStateException("AtResponse is NULL for '" + finalResult + "'");
//    }
  }

  public List<String> getInformationalText() {
    return informationalText;
  }

//  public boolean isOk() {
//    return FinalResponseCode.OK == finalResponseCode || FinalResponseCode.CONNECT == finalResponseCode || FinalResponseCode.CONNECTED == finalResponseCode;
//  }

  public String getStatus() {
    return finalResult;
  }

//  public FinalResponseCode getFinalResponseCode() {
//    return finalResponseCode;
//  }

  public AbstractFinalResponse getAbstractFinalResponse() {
    return abstractFinalResponse;
  }

}
