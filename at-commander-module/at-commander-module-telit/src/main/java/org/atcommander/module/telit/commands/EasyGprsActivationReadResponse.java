package org.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.module.telit.types.ContextStatus;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class EasyGprsActivationReadResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^#SGACT: (\\d),(\\d)$");

  private List<ContextStatus> contextStatuses = new ArrayList<>();

  public EasyGprsActivationReadResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      final Matcher m4 = PATTERN.matcher(line);
      if (m4.find()) {
        final int cid = Integer.parseInt(m4.group(1));
        final int status = Integer.parseInt(m4.group(2));
        contextStatuses.add(new ContextStatus(cid, status));
        return;
      }
      throw createParseException(line);
    }
  }

  public List<ContextStatus> getContextStatuses() {
    return contextStatuses;
  }
}
