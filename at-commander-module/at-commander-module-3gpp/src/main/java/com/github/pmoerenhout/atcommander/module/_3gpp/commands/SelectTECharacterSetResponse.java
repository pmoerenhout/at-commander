package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SelectTECharacterSetResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CSCS: \"(.*)\"$");
  private static final Pattern PATTERNLIST = Pattern.compile("^\\+CSCS: \\((.*)\\)$");

  private List<String> characterSets;

  public SelectTECharacterSetResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    characterSets = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        characterSets = Collections.singletonList(m.group(1));
        return;
      } else {
        final Matcher matcherList = PATTERNLIST.matcher(line);
        if (matcherList.find()) {
          final String[] splitted = StringUtils.split(matcherList.group(1), ',');
          for (int i = 0; i < splitted.length; i++) {
            characterSets.add(trimDoubleQuotes(splitted[i]));
          }
          return;
        }
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getCharacterSet() {
    if (characterSets.size() != 1) {
      throw new IllegalStateException("Expecting only one character set");
    }
    return characterSets.get(0);
  }

  public List<String> getCharacterSets() {
    return characterSets;
  }
}
