package com.github.pmoerenhout.atcommander.module.telit;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;
import com.github.pmoerenhout.atcommander.FinalResponseFactory;

public class TelitFinalFactory implements FinalResponseFactory {

  private static final String STRING_RELEASED = "RELEASED";

  public AbstractFinalResponse generate(final String line) {
    if (StringUtils.equals(STRING_RELEASED, line)) {
      return new ReleasedFinalResponse(line);
    }
    return null;
  }
}
//
//
//  EMPTY("^\r\n$", 0),
//  OK("^OK$", 0),
//  CONNECT("^CONNECT (.*)$", 1),
//  ERROR("^ERROR$", 0),
//  NO_CARRIER("^NO CARRIER$", 0),
//  NO_DIALTONE("^NO DIALTONE$", 0),
//  BUSY("^BUSY$", 0),
//  NO_ANSWER("^NO ANSWER$", 0),
//  CONNECTED("^CONNECTED$", 0),
//  RELEASED("^RELEASED$", 0),
//  CME_ERROR("^\\+CME ERROR: (.*)$", 1),
//  CMS_ERROR("^\\+CMS ERROR: (.*)$", 1),
//  MORE_DATA("^> $", 0);