package org.atcommander.api;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.atcommander.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadThread {

  private static final Logger LOG = LoggerFactory.getLogger(ReadThread.class);

  private List<UnsolicitedPatternClass> unsolicitedPatterns;
  private UnsolicitedResponseCallback unsolicitedResponseCallback;
  private SolicitedResponseCallback solicitedResponseCallback;

  private ReadThreadTask task;
  private Thread thread;

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

  private void publishUnsolicitedEvent(final String line) {
    try {
      for (final UnsolicitedPatternClass unsolicitedPatternClass : unsolicitedPatterns) {
        final Matcher m = unsolicitedPatternClass.getPattern().matcher(line);
        if (m.find()) {
          final Class<?> myClass = unsolicitedPatternClass.getClazz();
          final Constructor<?> constructor = myClass.getConstructor(String.class);
          final UnsolicitedResponse unsolicitedResponse = (UnsolicitedResponse) constructor.newInstance(new Object[]{ line });
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
                final int bytesRead = sourceChannel.read(dst);
                // LOG.debug("read {} bytes from sourceChannel", bytesRead);
                dst.flip();
                // LOG.debug("position {} limit {}", dst.position(), dst.limit());
                for (int i = dst.position(); i < dst.limit(); i++) {
                  final byte b = dst.get();
                  if (b == '\r' || b == '\n') {
                    sendLine();
                  } else if (b == ' ') {
                    //LOG.trace("received linebuffer {}", b,
                    //    ArrayUtils.subarray(lineBuffer.array(), 0, lineBuffer.position()));
                    if (previous == '>' && lineBuffer.position() == 1) {
                      lineBuffer.put(b);
                      // LOG.debug("linebuffer {}", b, ArrayUtils.subarray(lineBuffer.array(), 0, lineBuffer.position()));
                      sendLine();
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

    private void sendLine() {
      if (lineBuffer.position() != 0) {
        // There is some data in the line buffer...
        lineBuffer.flip();
        final byte[] lineArray = new byte[lineBuffer.limit()];
        lineBuffer.get(lineArray);
        final String line = new String(lineArray);
        if (isUnsolicited(line)) {
          if (LOG.isTraceEnabled()) {
            LOG.trace("Received unsolicited line: '{}'", Util.onlyPrintable(line.getBytes()));
          }
          publishUnsolicitedEvent(line);
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