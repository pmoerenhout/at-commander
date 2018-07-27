package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketLastClosure;

public class SocketLastClosureResponse extends BaseResponse implements Response {

  final static Pattern pattern = Pattern.compile("^#SLASTCLOSURE: ([1-6]),(\\d)$");

  private SocketLastClosure[] socketLastClosures;

  public SocketLastClosureResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<SocketLastClosure> arrayList = new ArrayList<>();
    for (final String line : response.getInformationalText()) {
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        final int sid = Integer.parseInt(m.group(1));
        final int cause = Integer.parseInt(m.group(2));
        arrayList.add(new SocketLastClosure(sid, cause));
      } else {
        throw createParseException(line);
      }
    }
    socketLastClosures = arrayList.toArray(new SocketLastClosure[arrayList.size()]);
  }

  public SocketLastClosure[] getSocketLastClosures() {
    return socketLastClosures;
  }
}
