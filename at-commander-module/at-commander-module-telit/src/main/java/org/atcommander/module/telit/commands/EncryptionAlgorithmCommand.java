package org.atcommander.module.telit.commands;

import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.AtCommander;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;

public class EncryptionAlgorithmCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_ENCRYPTION_ALGORITHM = "#ENCALG";

  private int gsmEncryptionAlgorithm;
  private int gprsEncryptionAlgorithm;

  public EncryptionAlgorithmCommand(final AtCommander atCommander, final int gsmEncryptionAlgorithm,
                                    final int gprsEncryptionAlgorithm) {
    super(COMMAND_ENCRYPTION_ALGORITHM, atCommander);
    this.gsmEncryptionAlgorithm = gsmEncryptionAlgorithm;
    this.gprsEncryptionAlgorithm = gprsEncryptionAlgorithm;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_ENCRYPTION_ALGORITHM);
      sb.append(EQUAL);
      sb.append(String.valueOf(gsmEncryptionAlgorithm));
      sb.append(COMMA);
      sb.append(String.valueOf(gprsEncryptionAlgorithm));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }


}
