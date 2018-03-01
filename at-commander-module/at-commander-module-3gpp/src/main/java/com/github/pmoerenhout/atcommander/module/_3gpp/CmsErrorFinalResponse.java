package com.github.pmoerenhout.atcommander.module._3gpp;

import com.github.pmoerenhout.atcommander.FinalResponse2;
import com.github.pmoerenhout.atcommander.module._3gpp.exceptions.CmsErrorException;

public class CmsErrorFinalResponse extends FinalResponse2 {

  private String error;

  public CmsErrorFinalResponse(final String line, final String error) {
    super(line);
    this.error = error;
  }

  @Override
  public void throwIfNeccessary() throws CmsErrorException {
    throw new CmsErrorException(error);
  }

}
