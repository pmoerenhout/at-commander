package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.module.telit.types.Ping;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class PingResponse extends BaseResponse implements Response {

  // #PING: 01,"8.8.8.8",5,52

  private static final Pattern PATTERN = Pattern.compile("^#PING: (\\d*),\"([0-9.]*)\",(\\d*),(\\d*)$");
  private Ping[] pings;

  public PingResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<Ping> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int replyNumber = Integer.parseInt(m.group(1));
        final String remoteIpAddress = m.group(2);
        final int replyTime = Integer.parseInt(m.group(3));
        final int timeToLive = Integer.parseInt(m.group(4));
        arrayList.add(new Ping(replyNumber, remoteIpAddress, replyTime, timeToLive));
      } else {
        throw createParseException(line);
      }
    }
    pings = arrayList.toArray(new Ping[arrayList.size()]);
  }

  public Ping[] getPings() {
    return pings;
  }
}
