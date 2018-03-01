package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketInformation;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SocketInformationResponse extends BaseResponse implements Response {

  final static Pattern pattern = Pattern.compile("^#SI: ([1-6]),(\\d*),(\\d*),(\\d*),(\\d*)$");
  private static final Logger LOG = LoggerFactory.getLogger(SocketInformationResponse.class);
  private SocketInformation[] socketInformations;

  public SocketInformationResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<SocketInformation> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      //LOG.info("LINE: {}", line);
      final Matcher m = pattern.matcher(line);

      if (m.find()) {
        final int sid = Integer.parseInt(m.group(1));
        final int sent = Integer.parseInt(m.group(2));
        final int received = Integer.parseInt(m.group(3));
        final int bufferedInput = Integer.parseInt(m.group(4));
        final int acknowledgedWaiting = Integer.parseInt(m.group(5));
        arrayList.add(new SocketInformation(sid, sent, received, bufferedInput, acknowledgedWaiting));
      } else {
        throw createParseException(line);
      }
    }
    socketInformations = arrayList.toArray(new SocketInformation[arrayList.size()]);
  }

  public SocketInformation[] getSocketInformations() {
    return socketInformations;
  }

}
