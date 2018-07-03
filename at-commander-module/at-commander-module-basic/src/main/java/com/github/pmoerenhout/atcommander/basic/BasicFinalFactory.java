package com.github.pmoerenhout.atcommander.basic;

import org.apache.commons.lang3.StringUtils;
import com.github.pmoerenhout.atcommander.ErrorFinalResponse;
import com.github.pmoerenhout.atcommander.AbstractFinalResponse;
import com.github.pmoerenhout.atcommander.FinalResponseFactory;
import com.github.pmoerenhout.atcommander.OkFinalResponse;

public class BasicFinalFactory implements FinalResponseFactory {

  private static final String STRING_OK = "OK";
  private static final String STRING_ERROR = "ERROR";

  public AbstractFinalResponse generate(final String line) {
    if (StringUtils.equals(STRING_OK, line)) {
      return new OkFinalResponse(line);
    }
    if (StringUtils.equals(STRING_ERROR, line)) {
      return new ErrorFinalResponse(line);
    }
    return null;
  }
}
