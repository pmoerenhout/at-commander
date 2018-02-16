package org.atcommander.module._3gpp;

import org.atcommander.FinalResponse2;
import org.atcommander.module._3gpp.exceptions.CmeErrorException;

public class CmeErrorFinalResponse extends FinalResponse2 {

  private String error;

  public CmeErrorFinalResponse(final String line, final String error) {
    super(line);
    this.error = error;
  }

  @Override
  public void throwIfNeccessary() throws CmeErrorException {
    throw new CmeErrorException(error);
  }

}
