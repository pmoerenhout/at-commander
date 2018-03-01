package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class MultiplexingModeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_MUX = "+CMUX";

  private int transparency;
  private Integer subset;
  private Integer portSpeed;
  private Integer n1;
  private Integer t1;
  private Integer n2;
  private Integer t2;
  private Integer t3;
  private Integer k;

  public MultiplexingModeCommand(final AtCommander atCommander, final int transparency) {
    super(COMMAND_MUX, atCommander);
    this.transparency = transparency;
  }

  public void setSubset(final Integer subset) {
    this.subset = subset;
  }

  public void setPortSpeed(final Integer portSpeed) {
    this.portSpeed = portSpeed;
  }

  public void setN1(final Integer n1) {
    this.n1 = n1;
  }

  public void setT1(final Integer t1) {
    this.t1 = t1;
  }

  public void setN2(final Integer n2) {
    this.n2 = n2;
  }

  public void setT2(final Integer t2) {
    this.t2 = t2;
  }

  public void setT3(final Integer t3) {
    this.t3 = t3;
  }

  public void setK(final Integer k) {
    this.k = k;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(BaseCommand.AT);
      sb.append(COMMAND_MUX);
      sb.append(EQUAL);
      sb.append(transparency);
      if (subset != null) {
        sb.append(COMMA);
        sb.append(subset);
        if (portSpeed != null) {
          sb.append(COMMA);
          sb.append(portSpeed);
          if (n1 != null) {
            sb.append(COMMA);
            sb.append(n1);
            if (t1 != null) {
              sb.append(COMMA);
              sb.append(t1);
              if (n2 != null) {
                sb.append(COMMA);
                sb.append(n2);
                if (t2 != null) {
                  sb.append(COMMA);
                  sb.append(t2);
                  if (t3 != null) {
                    sb.append(COMMA);
                    sb.append(t3);
                    if (k != null) {
                      sb.append(COMMA);
                      sb.append(k);
                    }
                  }
                }
              }
            }
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
