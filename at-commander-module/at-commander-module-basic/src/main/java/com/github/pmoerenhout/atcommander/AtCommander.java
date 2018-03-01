package com.github.pmoerenhout.atcommander;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.SolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.basic.BasicFinalFactory;
import com.github.pmoerenhout.atcommander.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtCommander implements SolicitedResponseCallback {

  private static final Logger LOG = LoggerFactory.getLogger(AtCommander.class);
  private static final byte[] NEWLINE = "\r".getBytes(StandardCharsets.US_ASCII);

  final List<String> lines = Collections.synchronizedList(new ArrayList<>());
  final List<FinalResponseFactory> finalResponseFactories = new ArrayList<>();
  private final Semaphore lock = new Semaphore(1, true);
  private final Semaphore finalReceived = new Semaphore(0);
  private final Semaphore linesLock = new Semaphore(1, true);
  private FinalResponse atResponseFinal;
  private SerialInterface serial;

  public AtCommander(final SerialInterface serial) {
    this.serial = serial;
    LOG.debug("AtCommander for serialId:{}", serial.getId());
    finalResponseFactories.add(new BasicFinalFactory());
  }

  public void addFinalResponseFactory(final FinalResponseFactory finalResponseFactory) {
    finalResponseFactories.add(finalResponseFactory);
  }

  public void init() throws SerialException {
    serial.init();
  }

  public void close() {
    serial.close();
  }

  public void write(final byte[] bytes) throws SerialException {
    LOG.debug("write {}", Util.onlyPrintable(bytes));
    serial.write(bytes);
  }

  private void panic() {
    serial.panic();
  }

  public AtResponse send(final String command, final long timeout) throws SerialException {
    return send(ArrayUtils.addAll(command.getBytes(), NEWLINE), timeout);
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
          LOG.info("The final response was not received within the timeout of {} milliseconds", timeout);
        }
      } catch (final InterruptedException e) {
        LOG.info("The final response was not received within the timeout of {} milliseconds", timeout);
        LOG.warn("Timeout after {} milliseconds: {}", timeout, e.getMessage());
      }
      if (lines.size() == 0) {
        panic();
        LOG.warn("No lines received!");
        final byte[] ttt = serial.read();
        LOG.trace("Read after writing {}: {} ({})", Util.onlyPrintable(bytes), ttt.length, Util.onlyPrintable(ttt));
        return null;
      }
      return new AtResponse(finalResponseFactories, lines);
    } finally {
      lock.release();
    }
  }

  public void solicited(final String response) {
    linesLock.acquireUninterruptibly();
    if (StringUtils.isNotBlank(response)) {
      lines.add(response);
    }
    atResponseFinal = FinalResponseCode.fromStringEx(response);
    if (atResponseFinal != null) {
      if (atResponseFinal.getCode() != FinalResponseCode.OK && atResponseFinal.getCode() != FinalResponseCode.MORE_DATA) {
        if (LOG.isTraceEnabled()) {
          if (atResponseFinal.getParameter() == null) {
            LOG.trace("Final response {} ({})", atResponseFinal.getCode(), response);
          } else {
            LOG.trace("Final response {} parameter '{}' ({})", atResponseFinal.getCode(), atResponseFinal.getParameter(), response);
          }
        }
      }
      finalReceived.release();
    }
    linesLock.release();
  }

}
