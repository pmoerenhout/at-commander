package com.github.pmoerenhout.atcommander.basic.commands;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;

public interface Response {

  void parse(AtResponse atResponse) throws ResponseException;

}
