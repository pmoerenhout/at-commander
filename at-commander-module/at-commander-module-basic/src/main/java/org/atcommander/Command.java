package org.atcommander;

import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public interface Command<T extends BaseResponse> {
  T set() throws SerialException, TimeoutException, ResponseException;

  Response read() throws SerialException, TimeoutException, ResponseException;

  Response test() throws SerialException, TimeoutException, ResponseException;

  String getType();
}
