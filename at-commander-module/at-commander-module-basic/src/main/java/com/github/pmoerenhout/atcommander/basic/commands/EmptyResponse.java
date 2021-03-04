package com.github.pmoerenhout.atcommander.basic.commands;

import java.util.List;

import com.github.pmoerenhout.atcommander.AtResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyResponse extends BaseResponse implements Response {

  public EmptyResponse(final AtResponse s) {
    this.parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() != 0) {
      for (final String line : informationalText) {
        log.info("Did not expect any response, but got '{}'", line);
      }
    }
  }
}