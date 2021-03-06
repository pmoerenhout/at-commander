package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class HttpRingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // #HTTPRING: <prof_id>,<http_status_code>,<content_type>,<data_size>
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("#HTTPRING: ([0-2]),(\\d*),\"(.*)\",(\\d*)$");

  private int profileId;
  private int httpStatusCode;
  private String contentType;
  private int dataSize;

  public HttpRingUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() ==1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        profileId = Integer.parseInt(m.group(1));
        httpStatusCode = Integer.parseInt(m.group(2));
        contentType = m.group(3);
        dataSize = Integer.parseInt(m.group(4));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public int getProfileId() {
    return profileId;
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }

  public String getContentType() {
    return contentType;
  }

  public int getDataSize() {
    return dataSize;
  }
}
