package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class GeneralPurposeInputOutputCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_GPIO = "#GPIO";

  private int pin;
  // 0=input
  private int mode;
  // 0=input, 1=output, 2-6=alternate 1-5
  private Integer direction;
  private Boolean save;

  public GeneralPurposeInputOutputCommand(final AtCommander atCommander, final int pin, final int mode) {
    super(COMMAND_GPIO, atCommander);
    this.pin = pin;
    this.mode = mode;
  }

  public GeneralPurposeInputOutputCommand(final AtCommander atCommander, final int pin, final int mode,
                                          final Integer direction) {
    super(COMMAND_GPIO, atCommander);
    this.pin = pin;
    this.mode = mode;
    this.direction = direction;
  }

  public GeneralPurposeInputOutputCommand(final AtCommander atCommander, final int pin, final int mode,
                                          final Integer direction, final boolean save) {
    super(COMMAND_GPIO, atCommander);
    this.pin = pin;
    this.mode = mode;
    this.direction = direction;
    this.save = save;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_GPIO);
      sb.append(EQUAL);
      sb.append(String.valueOf(pin));
      sb.append(COMMA);
      sb.append(String.valueOf(mode));
      if (direction != null) {
        sb.append(COMMA);
        sb.append(String.valueOf(direction));
        if (save != null) {
          sb.append(COMMA);
          sb.append(oneOrZero(save));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setDirection(final Integer direction) {
    this.direction = direction;
  }

  public void setSave(final Boolean save) {
    this.save = save;
  }
}
