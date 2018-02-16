package org.atcommander.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsolicitedFutureTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(UnsolicitedFutureTask.class);

  private UnsolicitedResponseCallback unsolicitedResponseCallback;
  private UnsolicitedResponse unsolicitedResponse;

  public UnsolicitedFutureTask(final UnsolicitedResponseCallback unsolicitedResponseCallback, final UnsolicitedResponse unsolicitedResponse) {
    this.unsolicitedResponseCallback = unsolicitedResponseCallback;
    this.unsolicitedResponse = unsolicitedResponse;
  }

  @Override
  public void run() {
    try {
      //LOG.info("Send the unsolicited response to callback {}", Thread.currentThread().getName());
      unsolicitedResponseCallback.unsolicited(unsolicitedResponse);
      // LOG.info("Send the unsolicited response to callback, done {}", Thread.currentThread().getName());
    } catch (Exception e) {
      LOG.error("Error execute callback unsolicited", e);
    }
    return;
  }
}