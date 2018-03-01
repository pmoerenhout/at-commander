package com.github.pmoerenhout.atcommander.module.telit;

import org.apache.commons.lang3.StringUtils;
import com.github.pmoerenhout.atcommander.FinalResponse2;
import com.github.pmoerenhout.atcommander.FinalResponseFactory;

public class TelitFinalFactory implements FinalResponseFactory {

  // ETSI TS 300 916

  private static final String STRING_MORE_DATA = "> ";

  public FinalResponse2 generate(final String line) {
    if (StringUtils.equals(STRING_MORE_DATA, line)){
      return new MoreDataFinalResponse(line);
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