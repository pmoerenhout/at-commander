package com.github.pmoerenhout.atcommander.jssc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import com.github.pmoerenhout.atcommander.common.Util;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsscSerialPortReader implements SerialPortEventListener {

  private SerialPort serialPort;
  private Pipe.SinkChannel sinkChannel;

  public JsscSerialPortReader(final SerialPort serialPort, final Pipe.SinkChannel sinkChannel) {
    this.serialPort = serialPort;
    this.sinkChannel = sinkChannel;
  }

  public void serialEvent(final SerialPortEvent event) {
    final String portName = event.getPortName();
    final int eventType = event.getEventType();
    final int eventValue = event.getEventValue();
    if (event.isRXCHAR()) {
      if (eventValue > 0) {
        try {
          final byte[] b = serialPort.readBytes(eventValue);
          if (log.isTraceEnabled()) {
            log.trace("Serial received {} bytes: '{}'", b.length, Util.onlyPrintable(b));
          }
          final ByteBuffer buf = ByteBuffer.wrap(b);
          while (buf.hasRemaining()) {
            final int bytesWritten = sinkChannel.write(buf);
            if (b.length != bytesWritten) {
              log.warn("Not all ({} of {}) bytes written to pipe", bytesWritten, b.length);
            }
          }
        } catch (final SerialPortException e) {
          log.error("SerialPort exception {} {} {} {}", e.getPortName(), e.getMethodName(), e.getExceptionType(), e.getMessage());
        } catch (final IOException e) {
          log.error("I/O exception", e);
        }
      } else {
        log.info("No serial data available to read");
      }
    } else if (event.isCTS()) {//If CTS line has changed state
      log.debug("CTS - {}", onOff(eventValue));
    } else if (event.isDSR()) {///If DSR line has changed state
      log.debug("DSR - {}", onOff(eventValue));
    } else if (event.isBREAK()) {
      log.debug("BREAK - {}", onOff(eventValue));
    } else if (event.isTXEMPTY()) {
      log.debug("TXEMPTY - {}", onOff(eventValue));
    } else if (event.isRLSD()) {
      log.debug("RLSD - {}", onOff(eventValue));
    } else if (event.isERR()) {
      log.debug("ERR - {}", onOff(eventValue));
    } else if (event.isRXFLAG()) {
      log.debug("RXFLAG - {}", onOff(eventValue));
    } else if (event.isRING()) {
      log.debug("RING - {}", onOff(eventValue));
    } else {
      log.error("Unknown serial event: {} {} {}", portName, eventType, eventValue);
    }
  }

  private String onOff(final int value) {
    if (value == 0) {
      return "OFF";
    }
    if (value == 1) {
      return "ON";
    }
    throw new RuntimeException("Unexpected value " + value + " received");
  }

}