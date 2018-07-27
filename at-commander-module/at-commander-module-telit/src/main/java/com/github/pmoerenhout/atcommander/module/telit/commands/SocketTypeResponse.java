package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.module.telit.enums.Direction;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketType;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module.v250.enums.Type;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class SocketTypeResponse extends BaseResponse implements Response {

  final static Pattern pattern = Pattern.compile("^#ST: ([1-6]),([0-2]),([0-2])$");
  private SocketType[] socketTypes;

  public SocketTypeResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<SocketType> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        final int sid = Integer.parseInt(m.group(1));
        final Type type = Type.fromInt(Integer.parseInt(m.group(2)));
        final Direction direction = Direction.fromInt(Integer.parseInt(m.group(3)));
        arrayList.add(new SocketType(sid, type, direction));
      }
      else {
        throw createParseException(line);
      }
    }
    socketTypes = arrayList.toArray(new SocketType[arrayList.size()]);
  }

  public SocketType[] getSocketTypes() {
    return socketTypes;
  }
}