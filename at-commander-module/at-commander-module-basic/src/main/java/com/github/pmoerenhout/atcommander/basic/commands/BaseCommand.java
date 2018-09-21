package com.github.pmoerenhout.atcommander.basic.commands;

import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;

public abstract class BaseCommand {

  protected static final String AT = "AT";
  protected static final char EQUAL = '=';
  protected static final char COMMA = ',';
  protected static final char QUERY = '?';
  protected static final char DOUBLE_QUOTE = '"';
  protected static final byte CTRLZ = (byte) 0x1a;
  protected static final Semaphore available = new Semaphore(1, true);

  private static final Logger LOG = LoggerFactory.getLogger(BaseCommand.class);
  private static final long DEFAULT_TIMEOUT = 60000;
  private static final byte[] NEWLINE = "\r".getBytes();

  private final String type;
  private final AtCommander atCommander;
  private long timeout;

  public BaseCommand(final String type, final AtCommander atCommander) {
    this.type = type;
    this.atCommander = atCommander;
    this.timeout = DEFAULT_TIMEOUT;
  }

  protected static char oneOrZero(final boolean b) {
    return b ? '1' : '0';
  }

  protected String doubleQuoted(final String s) {
    return DOUBLE_QUOTE + s + DOUBLE_QUOTE;
  }

  public Response read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(type);
      sb.append(QUERY);
      return new SimpleResponse(execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public Response test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(type);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new SimpleResponse(execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public AtResponse execute(final String command) throws SerialException, TimeoutException, ResponseException {
    final AtResponse s = execute(ArrayUtils.addAll(command.getBytes(), NEWLINE), timeout);
    if (s == null) {
      throw new RuntimeException("The response was NULL, after execute '" + command + "'");
    }
    return s;
  }

  public AtResponse execute(final byte[] bytes) throws SerialException, TimeoutException, ResponseException {
    return execute(bytes, timeout);
  }

  public AtResponse execute(final byte b, final long timeout) throws SerialException, TimeoutException, ResponseException {
    return execute(new byte[]{ b }, timeout);
  }

  public AtResponse execute(final byte b) throws SerialException, TimeoutException, ResponseException {
    return execute(new byte[]{ b }, timeout);
  }

  public AtResponse execute(final byte[] bytes, final long timeout) throws SerialException, TimeoutException, ResponseException {
    final AtResponse response = atCommander.send(bytes, timeout);
    if (response == null) {
      LOG.warn("Timeout on execute command: {} [{}] {}ms", Util.onlyPrintable(bytes), Util.bytesToHexString(bytes), timeout);
      throw new TimeoutException(
          "Timeout on command " + Util.onlyPrintable(bytes) + " (" + Util.bytesToHexString(bytes) + ") after " + timeout + "ms");
    }
//    if (response.isOk()) {
//      return response;
//    }
//    if (response.getFinalResponseCode() == null) {
//      LOG.warn("getFinalResponse is NULL, on {} {} status:{}", Util.onlyPrintable(bytes), Util.bytesToHexString(bytes), response.getStatus());
//      LOG.warn("getFinalResponse is NULL, resp {}, final {}", response.getInformationalText(), response.getFinalResponseCode());
//      final List<String> lines = response.getInformationalText();
//      for (final String line : lines) {
//        LOG.debug("Response line: {}", line);
//      }
//    }

    final AbstractFinalResponse abstractFinalResponse = response.getAbstractFinalResponse();
    abstractFinalResponse.throwIfNeccessary();
    return response;

//    final FinalResponseCode finalResponseCode = response.getFinalResponseCode();
//    switch (finalResponseCode) {
//      case OK:
//        LOG.warn("OK, but isOk was false?");
//        return response;
//      case CONNECT:
//        LOG.warn("CONNECT, but isOk is {}", response.isOk());
//        return response;
//      case BUSY:
//        LOG.trace("BUSY exception");
//        throw new BusyException(response.getStatus());
//      case ERROR:
//        LOG.trace("ERROR exception");
//        throw new ErrorException("Command " + type + " resulted in " + response.getStatus());
//      case NO_CARRIER:
//        LOG.trace("NO CARRIER exception");
//        final List<String> informationalText = response.getInformationalText();
//        for (String line : informationalText) {
//          LOG.debug("NO CARRIER Response line: {}", line);
//        }
//        // throw new NoCarrierException(response.getLine());
//        return response;
//      case NO_DIALTONE:
//        LOG.trace("NO DIALTONE Exception: {}", response.getStatus());
//        throw new NoDialToneException(response.getStatus());
//      case CME_ERROR:
//        LOG.warn("CME ERROR exception: {}", response.getStatus());
//        //LOG.info("CME ERROR resp status {} {}", response.getStatus(), response.getStatus().getClass().getName());
//        final MobileEquipmentErrorResponse mobileEquipmentErrorResponse = new MobileEquipmentErrorResponse(
//            response.getStatus());
//        throw new MobileEquipmentErrorException(mobileEquipmentErrorResponse.getMessage(),
//            mobileEquipmentErrorResponse.getCode());
//      case CMS_ERROR:
//        LOG.warn("CMS ERROR exception: {}", response.getStatus());
//        throw new MessageServiceErrorException(response.getStatus());
//      case MORE_DATA:
//        LOG.trace("Modem is ready to accept more data ({})", response.getStatus());
//        return response;
//      default:
//        LOG.warn("Final Response {} not recognized", finalResponseCode);
//        return response;
//    }
  }

  public void writeBytes(final byte[] bytes) throws SerialException, ResponseException {
    LOG.debug("Send {}", Util.bytesToHexString(bytes));
    atCommander.write(bytes);
  }

  public String getType() {
    return type;
  }

  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(final long timeout) {
    this.timeout = timeout;
  }

}
