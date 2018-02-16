package org.atcommander.module.v250;

import org.atcommander.FinalResponse2;
import org.atcommander.module.v250.types.DialStatus;

public class DialFinalResponse extends FinalResponse2 {

  protected DialStatus status;
  protected String text;

  public DialFinalResponse(final String line, final DialStatus status) {
    super(line);
    this.status = status;
  }

  public DialFinalResponse(final String line, final DialStatus status, final String text) {
    this(line, status);
    this.text = text;
  }

  @Override
  public void throwIfNeccessary() {
    // The caller should check the dial status
    return;
  }

  public DialStatus getStatus() {
    return status;
  }

  public String getText() {
    return text;
  }
}
