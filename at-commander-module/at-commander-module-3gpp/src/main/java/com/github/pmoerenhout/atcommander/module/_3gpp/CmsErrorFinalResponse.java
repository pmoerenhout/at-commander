package com.github.pmoerenhout.atcommander.module._3gpp;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.exceptions.CmsErrorException;

public class CmsErrorFinalResponse extends AbstractFinalResponse {

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
