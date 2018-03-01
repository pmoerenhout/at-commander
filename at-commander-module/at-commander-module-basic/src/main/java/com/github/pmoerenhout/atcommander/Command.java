package com.github.pmoerenhout.atcommander;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public interface Command<T extends BaseResponse> {
  T set() throws SerialException, TimeoutException, ResponseException;

  Response read() throws SerialException, TimeoutException, ResponseException;

  Response test() throws SerialException, TimeoutException, ResponseException;

  String getType();
}
