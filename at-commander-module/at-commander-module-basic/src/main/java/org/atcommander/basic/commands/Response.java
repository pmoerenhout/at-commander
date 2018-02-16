package org.atcommander.basic.commands;

import org.atcommander.AtResponse;
import org.atcommander.basic.exceptions.ResponseException;

public interface Response {

  void parse(AtResponse atResponse) throws ResponseException;

}
