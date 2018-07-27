package com.github.pmoerenhout.atcommander.api;

import java.util.List;

public interface UnsolicitedResponse {

  void parseUnsolicited(List<String> lines);

}