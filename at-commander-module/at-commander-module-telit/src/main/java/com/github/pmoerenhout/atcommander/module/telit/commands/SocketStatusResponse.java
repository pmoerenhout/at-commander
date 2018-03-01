package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.module.telit.enums.SocketState;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketStatus;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class SocketStatusResponse extends BaseResponse implements Response {

  // #SS: 5,2,10.141.251.103,7,195.8.209.155,7

  private static final Pattern PATTERN1 = Pattern.compile("^#SS: ([1-6]),(\\d)$");
  private static final Pattern PATTERN2 = Pattern.compile("^#SS: ([1-6]),(\\d),([0-9.]*),(\\d*)$");
  private static final Pattern PATTERN3 = Pattern.compile("^#SS: ([1-6]),(\\d),([0-9.]*),(\\d*),([0-9.]*),(\\d*)$");
  private SocketStatus[] socketStatuses;

  public SocketStatusResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<SocketStatus> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
//      LOG.info("LINE: {}", line);
      final Matcher m1 = PATTERN1.matcher(line);
      final Matcher m2 = PATTERN2.matcher(line);
      final Matcher m3 = PATTERN3.matcher(line);
      if (m1.find()) {
        final int sid = Integer.parseInt(m1.group(1));
        final SocketState state = SocketState.fromInt(Integer.parseInt(m1.group(2)));
        arrayList.add(new SocketStatus(sid, state));
      } else if (m2.find()) {
        final int sid = Integer.parseInt(m2.group(1));
        final SocketState state = SocketState.fromInt(Integer.parseInt(m2.group(2)));
        final String localIpAddress = m2.group(3);
        final int localPort = Integer.parseInt(m2.group(4));
        arrayList.add(new SocketStatus(sid, state, localIpAddress, localPort));
      } else if (m3.find()) {
        final int sid = Integer.parseInt(m3.group(1));
        final SocketState state = SocketState.fromInt(Integer.parseInt(m3.group(2)));
        final String localIpAddress = m3.group(3);
        final int localPort = Integer.parseInt(m3.group(4));
        final String remoteIpAddress = m3.group(5);
        final int remotePort = Integer.parseInt(m3.group(6));
        arrayList.add(new SocketStatus(sid, state, localIpAddress, localPort, remoteIpAddress, remotePort));
      } else {
        throw createParseException(line);
      }
    }
    socketStatuses = arrayList.toArray(new SocketStatus[arrayList.size()]);
  }

  public SocketStatus[] getSocketStatuses() {
    return socketStatuses;
  }

}
