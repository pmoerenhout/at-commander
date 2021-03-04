package com.github.pmoerenhout.atcommander;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.SolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.basic.BasicFinalFactory;
import com.github.pmoerenhout.atcommander.common.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtCommander implements SolicitedResponseCallback {

  final List<String> lines = Collections.synchronizedList(new ArrayList<>());
  final List<FinalResponseFactory> finalResponseFactories = new ArrayList<>();
  private final Semaphore lock = new Semaphore(1, true);
  private final Semaphore finalReceived = new Semaphore(0);
  private final Semaphore linesLock = new Semaphore(1, true);
  private FinalResponse atResponseFinal;
  private SerialInterface serial;
  private OutputStream serialOutputStream;

  public AtCommander(final SerialInterface serial) {
    this.serial = serial;
    finalResponseFactories.add(new BasicFinalFactory());
  }

  public void addFinalResponseFactory(final FinalResponseFactory finalResponseFactory) {
    finalResponseFactories.add(finalResponseFactory);
  }

  public void init() throws SerialException {
    serial.init();
    serialOutputStream = serial.getOutputStream();
  }

  public void close() {
    log.debug("close");
    serial.close();
  }

  public void write(final byte[] bytes) throws SerialException {
    int tries = 5;
    while (tries-- > 0 && !serial.isCts()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        log.error("Interrupted", e);
      }
    }
    if (tries == 0) {
      log.info("Serial: CTS:{} DSR:{} CD:{}", serial.isCts(), serial.isDsr(), serial.isCd());
      log.debug("Waiting 500ms for CTS signal, but didn't get it");
    }
    log.debug("write {} ({})", Util.onlyPrintable(bytes), bytes.length);
    try {
      serialOutputStream.write(bytes);
    } catch (final IOException e) {
      throw new SerialException(e);
    }
  }

  private void panic() {
    serial.panic();
  }

  public AtResponse send(final byte[] bytes, final long timeout) throws SerialException {
    lock.acquireUninterruptibly();
    try {
      finalReceived.drainPermits();
      lines.clear();
      write(bytes);
      try {
        final boolean isFinalReceived = finalReceived.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!isFinalReceived) {
          log.info("The final response was not received within the timeout of {} milliseconds", timeout);
        }
      } catch (final InterruptedException e) {
        log.info("The final response was not received within the timeout of {} milliseconds", timeout);
        log.warn("Timeout after {} milliseconds: {}", timeout, e.getMessage());
      }
      if (lines.size() == 0) {
        panic();
        log.warn("No lines received!");
        final byte[] bytesRead = serial.read();
        log.trace("Read after writing {}: {} ({})", Util.onlyPrintable(bytes), Util.onlyPrintable(bytesRead), bytesRead.length);
        return null;
      }
      return new AtResponse(finalResponseFactories, lines);
    } finally {
      lock.release();
    }
  }

  public void solicited(final String response) {
    linesLock.acquireUninterruptibly();
    if (response.length() != 0) {
      lines.add(response);
    }
    atResponseFinal = FinalResponseCode.fromStringEx(response);
    if (atResponseFinal != null) {
      if (atResponseFinal.getCode() != FinalResponseCode.OK && atResponseFinal.getCode() != FinalResponseCode.CONNECT && atResponseFinal
          .getCode() != FinalResponseCode.MORE_DATA) {
        if (log.isTraceEnabled()) {
          if (atResponseFinal.getParameter() == null) {
            log.trace("Final response {} ({})", atResponseFinal.getCode(), response);
          } else {
            log.trace("Final response {} parameter '{}' ({})", atResponseFinal.getCode(), atResponseFinal.getParameter(), response);
          }
        }
      }
      finalReceived.release();
    }
    linesLock.release();
  }

}