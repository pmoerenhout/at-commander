package com.github.pmoerenhout.atcommander.module.v250;

import com.github.pmoerenhout.atcommander.FinalResponse2;
import com.github.pmoerenhout.atcommander.basic.exceptions.ErrorException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.BusyException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.NoAnswerException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.NoCarrierException;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.NoDialToneException;
import com.github.pmoerenhout.atcommander.module.v250.types.DialStatus;

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
  public void throwIfNeccessary() throws ResponseException {
    switch (status) {
      case ERROR:
        throw new ErrorException();
      case NO_CARRIER:
        throw new NoCarrierException();
      case BUSY:
        throw new BusyException();
      case NO_ANSWER:
        throw new NoAnswerException();
      case NO_DIALTONE:
        throw new NoDialToneException();
    }
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
