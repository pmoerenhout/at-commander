package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class SocketRingResponse extends BaseResponse implements UnsolicitedResponse {

  // SRING: 6
  // SRING: "195.8.209.155",7,6,69,0,2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054
  // SRING: "195.8.209.155",7,6,69,0,2015-06-08T10:25:07.7762015-06-08T10:25:07.7762015-06-08T10:25:07.777

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^SRING: (.*)$");
  private static final Pattern PATTERN1 = Pattern.compile("^SRING: ([1-6])$");
  private static final Pattern PATTERN2 = Pattern.compile("^SRING: ([1-6]),(\\d*)$");
  private static final Pattern PATTERN3 = Pattern.compile("^SRING: ([1-6]),(\\d*),(.*)$");
  private static final Pattern PATTERN4 = Pattern.compile("^SRING: \"([0-9.]*)\",(\\d*),([1-6]),(\\d*),(\\d*),(.*)$");

  private int socketId;
  private String sourceAddress;
  private Integer sourcePort;
  private Integer receivedData;
  private Integer dataLeft;
  private String data;

  public SocketRingResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  private void parse(final String line) {
    final Matcher m1 = PATTERN1.matcher(line);
    if (m1.find()) {
      socketId = Integer.parseInt(m1.group(1));
      return;
    }
    final Matcher m2 = PATTERN2.matcher(line);
    if (m2.find()) {
      socketId = Integer.parseInt(m2.group(1));
      receivedData = Integer.parseInt(m2.group(2));
      return;
    }
    final Matcher m3 = PATTERN3.matcher(line);
    if (m3.find()) {
      socketId = Integer.parseInt(m3.group(1));
      receivedData = Integer.parseInt(m3.group(2));
      data = m3.group(3);
      return;
    }
    final Matcher m4 = PATTERN4.matcher(line);
    if (m4.find()) {
      sourceAddress = m4.group(1);
      sourcePort = Integer.parseInt(m4.group(2));
      socketId = Integer.parseInt(m4.group(3));
      receivedData = Integer.parseInt(m4.group(4));
      dataLeft = Integer.parseInt(m4.group(5));
      data = m4.group(6);
      return;
    }
    throw new ParseException("Could not parse response: " + line);
  }

  public int getSocketId() {
    return socketId;
  }

  public String getSourceAddress() {
    return sourceAddress;
  }

  public Integer getSourcePort() {
    return sourcePort;
  }

  public Integer getReceivedData() {
    return receivedData;
  }

  public Integer getDataLeft() {
    return dataLeft;
  }

  public String getData() {
    return data;
  }
}
