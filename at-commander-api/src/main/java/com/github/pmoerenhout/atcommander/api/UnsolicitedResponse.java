package com.github.pmoerenhout.atcommander.api;

import java.util.List;
import java.util.regex.Pattern;

public interface UnsolicitedResponse {

  Pattern getPattern();

  void parseUnsolicited(List<String> lines);

}