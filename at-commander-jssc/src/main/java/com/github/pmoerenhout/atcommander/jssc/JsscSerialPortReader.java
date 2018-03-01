package com.github.pmoerenhout.atcommander.jssc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.common.Util;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class JsscSerialPortReader implements SerialPortEventListener {

  private static final Logger LOG = LoggerFactory.getLogger(JsscSerialPortReader.class);

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
          if (LOG.isTraceEnabled()) {
            LOG.trace("Serial received {} bytes: '{}'", b.length, Util.onlyPrintable(b));
          }
          LOG.debug("Serial received {} bytes: '{}'", b.length, Util.onlyPrintable(b));
          final ByteBuffer buf = ByteBuffer.wrap(b);
          while (buf.hasRemaining()) {
            final int bytesWritten = sinkChannel.write(buf);
            if (b.length != bytesWritten) {
              LOG.warn("Not all ({} of {}) bytes written to pipe", bytesWritten, b.length);
            }
          }
        } catch (final SerialPortException e) {
          LOG.error("SerialPort exception {} {} {} {}", e.getPortName(), e.getMethodName(), e.getExceptionType(), e.getMessage());
        } catch (final IOException e) {
          LOG.error("I/O exception", e);
        }
      } else {
        LOG.info("No serial data available to read");
      }
    } else if (event.isCTS()) {//If CTS line has changed state
      LOG.info("CTS - {}", onOff(eventValue));
    } else if (event.isDSR()) {///If DSR line has changed state
      LOG.info("DSR - {}", onOff(eventValue));
    } else if (event.isBREAK()) {
      LOG.info("BREAK - {}", onOff(eventValue));
    } else if (event.isTXEMPTY()) {
      LOG.info("TXEMPTY - {}", onOff(eventValue));
    } else if (event.isRLSD()) {
      LOG.info("RLSD - {}", onOff(eventValue));
    } else if (event.isERR()) {
      LOG.info("ERR - {}", onOff(eventValue));
    } else if (event.isRXFLAG()) {
      LOG.info("RXFLAG - {}", onOff(eventValue));
    } else if (event.isRING()) {
      LOG.info("RING - {}", onOff(eventValue));
    } else {
      LOG.error("Unknown serial event: {} {} {}", portName, eventType, eventValue);
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