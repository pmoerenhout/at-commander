package com.github.pmoerenhout.atcommander.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnsolicitedFutureTask implements Runnable {

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
      log.error("Error execute callback unsolicited", e);
    }
    return;
  }
}