package com.github.pmoerenhout.atcommander.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.common.Util;

public class ReadThread {

  private static final Logger LOG = LoggerFactory.getLogger(ReadThread.class);

  private List<UnsolicitedPatternClass> unsolicitedPatterns;
  private UnsolicitedResponseCallback unsolicitedResponseCallback;
  private SolicitedResponseCallback solicitedResponseCallback;

  private ReadThreadTask task;
  private Thread thread;

  private Mode mode;
  private InputStream dataInputStream;
  private OutputStream dataOutputStream;

  private int fetchAdditionalLines = 0;
  private List<String> additionalLines = new ArrayList<>();

  public ReadThread(final List<UnsolicitedPatternClass> unsolicitedPatterns,
                    final UnsolicitedResponseCallback unsolicitedResponseCallback,
                    final SolicitedResponseCallback solicitedResponseCallback) {
    this.unsolicitedPatterns = unsolicitedPatterns;
    this.unsolicitedResponseCallback = unsolicitedResponseCallback;
    this.solicitedResponseCallback = solicitedResponseCallback;
  }

  public void execute(final String id, final Pipe.SourceChannel sourceChannel) {
    task = new ReadThreadTask(sourceChannel);
    thread = (new Thread(task));
    thread.setName("ReadThread_" + id);
    thread.start();
  }

  public void close() throws InterruptedException {
    task.close();
    for (int i = 0; i < 100; i++) {
      Thread.sleep(10);
      if (thread.getState() == Thread.State.TERMINATED) {
        break;
      }
      LOG.info("Thread {} state is {}", thread.getId(), thread.getState());
    }
  }

  private void publishSolicitedEvent(final String line) {
    solicitedResponseCallback.solicited(line);
  }

  private void publishUnsolicitedEvent(final List<String> lines) {
    try {
      final String firstLine = lines.get(0);
      for (final UnsolicitedPatternClass unsolicitedPatternClass : unsolicitedPatterns) {
        final Matcher m = unsolicitedPatternClass.getPattern().matcher(firstLine);
        if (m.find()) {
          final Class<?> myClass = unsolicitedPatternClass.getClazz();
          final Constructor<?> constructor = myClass.getConstructor();
          final UnsolicitedResponse unsolicitedResponse = (UnsolicitedResponse) constructor.newInstance(new Object[]{});
          unsolicitedResponse.parseUnsolicited(lines);
          doUnsolicited(unsolicitedResponse);
        }
      }
    } catch (final NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      LOG.error("Could not generate unsolicited response", e);
    }
  }

  private void doUnsolicited(final UnsolicitedResponse unsolicitedResponse) {
    final Thread t = new Thread((new UnsolicitedFutureTask(unsolicitedResponseCallback, unsolicitedResponse)));
    t.start();
  }

  private class ReadThreadTask implements Runnable {
    ByteBuffer dst;
    ByteBuffer lineBuffer;
    Selector selector = null;

    private Pipe.SourceChannel sourceChannel;

    public ReadThreadTask(final Pipe.SourceChannel sourceChannel) {
      this.sourceChannel = sourceChannel;
    }

    public void close() {
      try {
        if (selector != null) {
          selector.close();
        } else {
          LOG.info("Selector is null");
        }
      } catch (IOException e) {
        LOG.error("I/O exception during close", e);
      }
      return;
    }

    public void run() {
      dst = ByteBuffer.allocate(2048);
      lineBuffer = ByteBuffer.allocate(2048);
      try {
        sourceChannel.configureBlocking(false);
        selector = SelectorProvider.provider().openSelector();
        final SelectionKey key = sourceChannel.register(selector, SelectionKey.OP_READ);
        while (true) {
          byte previous = 0x00;
          int n = selector.select(5000);
          //LOG.debug("Select => {} Pipe is open? {}", n, sourceChannel.isOpen());
          if (!sourceChannel.isOpen()) {
            LOG.debug("Sourcechannel is not open, exiting...");
            break;
          }
          if (n > 0) {
            final Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
              final SelectionKey key2 = it.next();
              it.remove();
              if (key2.isReadable()) {

                if (mode == Mode.DATA) {
                  final int bytesRead = sourceChannel.read(dst);
                  LOG.trace("read {} bytes from sourceChannel", bytesRead);
                  dst.flip();
                  Pipe.SinkChannel s = null;
                  dataOutputStream = Channels.newOutputStream(s);
                  s.write(dst);
                  continue;
                }

                final int bytesRead = sourceChannel.read(dst);
                LOG.trace("read {} bytes from sourceChannel", bytesRead);
                dst.flip();
                // LOG.debug("position {} limit {}", dst.position(), dst.limit());
                LOG.trace("dst: {}", Util.onlyPrintable(ArrayUtils.subarray(dst.array(), dst.position(), dst.limit())));
                for (int i = dst.position(); i < dst.limit(); i++) {

                  final byte b = dst.get();
                  // /x0d/x0a
                  if (b == '\n') {
                    if (previous == '\r') {
                      sendLine(true);
                    }
                  } else if (b == ' ') {
                    //LOG.trace("received linebuffer {}", b,
                    //    ArrayUtils.subarray(lineBuffer.array(), 0, lineBuffer.position()));
                    if (previous == '>' && lineBuffer.position() == 1) {
                      lineBuffer.put(b);
                      // LOG.debug("linebuffer {}", b, ArrayUtils.subarray(lineBuffer.array(), 0, lineBuffer.position()));
                      sendLine(false);
                    } else {
                      lineBuffer.put(b);
                    }
                  } else {
                    // Put into the line buffer...
                    lineBuffer.put(b);
                  }
                  previous = b;
                }
                dst.compact();
              }
            }
          }
        }
      } catch (final ClosedChannelException e) {
        LOG.debug("ClosedChannel exception");
      } catch (final IOException e) {
        LOG.error("I/O exception", e);
      } catch (final Exception e) {
        LOG.error("Exception", e);
      } finally {
        try {
          selector.close();
        } catch (final IOException e) {
          LOG.error("I/O exception on selector close: {} {}", e.getMessage());
        }
      }
    }

    private void sendLine(final boolean chopLastByte) {
      if (lineBuffer.position() != 0) {
        // There is some data in the line buffer...
        lineBuffer.flip();
        final byte[] lineArray;
        if (chopLastByte) {
          lineArray = new byte[lineBuffer.limit() - 1];
          lineBuffer.get(lineArray);
          // eat last char
          lineBuffer.get();
        } else {
          lineArray = new byte[lineBuffer.limit()];
          lineBuffer.get(lineArray);
        }
        if (lineArray.length == 0) {
          // empty line
          // TODO: instead of compact read more
          lineBuffer.compact();
          return;
        }
        // TODO: Read ASCII, or Latin1?
        final String line = new String(lineArray);
        if (fetchAdditionalLines > 0) {
          LOG.trace("Received line: '{}' (additional:{})", Util.onlyPrintable(line.getBytes()), fetchAdditionalLines);
        } else {
          LOG.trace("Received line: '{}'", Util.onlyPrintable(line.getBytes()));
        }
        if ("CONNECT".equals(line)) {
          LOG.debug("CONNECT received, go to DATA mode");
          mode = Mode.DATA;
        }
        if (fetchAdditionalLines > 0) {
          fetchAdditionalLines--;
          additionalLines.add(line);
          if (fetchAdditionalLines == 0) {
            // Multi line unsolicited
            LOG.trace("Publish {} unsolicited lines: '{}'", additionalLines.size(), additionalLines);
            publishUnsolicitedEvent(additionalLines);
            additionalLines.clear();
          }
        } else if (isUnsolicited(line)) {
          final int numberOfAdditionalLines = getNumberOfAdditionalLines(line);
          if (LOG.isTraceEnabled()) {
            LOG.trace("Received unsolicited line: '{}' with {} additional lines", Util.onlyPrintable(line.getBytes()), numberOfAdditionalLines);
          }
          if (numberOfAdditionalLines > 0) {
            fetchAdditionalLines = numberOfAdditionalLines;
            additionalLines.add(line);
          } else {
            // LOG.debug("Publish unsolicited line: '{}'", line);
            publishUnsolicitedEvent(Collections.singletonList(line));
          }
        } else {
          if (LOG.isTraceEnabled()) {
            LOG.trace("Received informational line: '{}'", Util.onlyPrintable(line.getBytes()));
          }
          publishSolicitedEvent(line);
        }
        lineBuffer.compact();
      }
    }

    private boolean isUnsolicited(final String line) {
      for (final UnsolicitedPatternClass pattern : unsolicitedPatterns) {
        if (pattern.getPattern().matcher(line).matches()) {
          return true;
        }
      }
      return false;
    }

    private int getNumberOfAdditionalLines(final String line) {
      for (final UnsolicitedPatternClass pattern : unsolicitedPatterns) {
        if (pattern.getPattern().matcher(line).matches()) {
          return pattern.getNumberOfAdditionalLines();
        }
      }
      throw new IllegalStateException("Couldn't determine number of lines for " + line);
    }

    public String getLine() {
      int skippedCharacters = 0;
      dst.flip();
      byte[] array = dst.array();
      for (int i = dst.position(); i < dst.limit(); i++) {
        if (array[i] == '\r' || array[i] == '\n') {
          skippedCharacters++;
          dst.get();
        } else {
          break;
        }
      }
      LOG.debug("Skipped {} characters, position {}, limit {}", skippedCharacters, dst.position(), dst.limit());
      for (int i = dst.position(); i < dst.limit(); i++) {
        LOG.debug("i {}", i);
        if (array[i] == '\r' || array[i] == '\n') {
          byte[] y = new byte[i];
          LOG.trace("eat {} bytes", y.length);
          dst.get(y);
          LOG.debug("i {}, position {}, limit {}", i, dst.position(), dst.limit());
          for (int j = i; j < dst.limit(); j++) {
            LOG.debug("i {}  j {}", i, j);
            if (array[j] == '\r' || array[j] == '\n') {
              LOG.trace("eat");
              byte bb = dst.get();
              LOG.trace("eat got {}", String.format("%02X", bb));
            } else {
              break;
            }
          }
          dst.compact();
          return new String(y);
        }
      }
      dst.compact();
      return null;
    }
  }
}