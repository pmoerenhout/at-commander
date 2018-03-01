package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SendMessageFromStorageCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SEND_MESSAGE_FROM_STORAGE = "+CMSS";

  private int index;
  private String destinationAddress;
  private Integer typeOfDestinationAddress;

  public SendMessageFromStorageCommand(final AtCommander atCommander, final int index) {
    super(COMMAND_SEND_MESSAGE_FROM_STORAGE, atCommander);
    this.index = index;
  }

  public SendMessageFromStorageCommand(final AtCommander atCommander, final int index, final String destinationAddress) {
    super(COMMAND_SEND_MESSAGE_FROM_STORAGE, atCommander);
    this.index = index;
    this.destinationAddress = destinationAddress;
  }

  public SendMessageFromStorageCommand(final AtCommander atCommander, final int index, final String destinationAddress, final int typeOfDestinationAddress) {
    super(COMMAND_SEND_MESSAGE_FROM_STORAGE, atCommander);
    this.index = index;
    this.destinationAddress = destinationAddress;
    this.typeOfDestinationAddress = typeOfDestinationAddress;
  }

  public SendMessageFromStorageResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SEND_MESSAGE_FROM_STORAGE);
      sb.append(EQUAL);
      sb.append(Integer.toString(index));
      if (destinationAddress != null) {
        sb.append(COMMA);
        sb.append(destinationAddress);
        if (typeOfDestinationAddress != null) {
          sb.append(COMMA);
          sb.append(Integer.toString(typeOfDestinationAddress));
        }
      }
      return new SendMessageFromStorageResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
