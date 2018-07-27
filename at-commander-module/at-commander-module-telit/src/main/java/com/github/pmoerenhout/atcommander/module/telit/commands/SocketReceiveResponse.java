package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SocketReceiveResponse extends BaseResponse implements Response {

  // HEXADECIMAL only

  final static Pattern pattern1 = Pattern.compile("^#SRECV: ([1-6]),(\\d*)$");
  final static Pattern pattern2 = Pattern.compile("^([0-9A-F]*)$");
  private static final Logger LOG = LoggerFactory.getLogger(SocketReceiveResponse.class);

  private int socketId;
  private int receivedData;
  private String data;

  public SocketReceiveResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 2) {
      final String line1 = informationalText.get(0);
      final Matcher m1 = pattern1.matcher(line1);
      if (m1.find()) {
        socketId = Integer.valueOf(m1.group(1));
        receivedData = Integer.valueOf(m1.group(2));
      }
      else {
        throw createParseException(line1);
      }

      final String line2 = informationalText.get(1);
      final Matcher m2 = pattern2.matcher(line2);
      if (m2.find()) {
        data = m2.group(1);
        if (data.length() != receivedData * 2) {
          LOG.warn("Data length difference: data:{} received:{}", data.length(), receivedData);
        }
        return;
      }
      throw createParseException(line2);
    }
  }

  public int getSocketId() {
    return socketId;
  }

  public int getReceivedData() {
    return receivedData;
  }

  public String getData() {
    return data;
  }
}
