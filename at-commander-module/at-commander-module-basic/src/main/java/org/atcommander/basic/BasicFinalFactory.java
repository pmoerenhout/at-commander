package org.atcommander.basic;

import org.apache.commons.lang3.StringUtils;
import org.atcommander.ErrorFinalResponse;
import org.atcommander.FinalResponse2;
import org.atcommander.FinalResponseFactory;
import org.atcommander.OkFinalResponse;

public class BasicFinalFactory implements FinalResponseFactory {

  private static final String STRING_OK = "OK";
  private static final String STRING_ERROR = "ERROR";

  public FinalResponse2 generate(final String line) {
    if (StringUtils.equals(STRING_OK, line)) {
      return new OkFinalResponse(line);
    }
    if (StringUtils.equals(STRING_ERROR, line)) {
      return new ErrorFinalResponse(line);
    }
    return null;
  }
}
