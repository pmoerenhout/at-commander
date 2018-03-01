package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class IndicatorControlCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_INDICATOR_CONTROL = "+CIND";

  private boolean batteryChargeLevelState;
  private Boolean signalQualityState;
  private Boolean serviceAvailabilityState;
  private Boolean sounderActivityState;
  private Boolean messageReceivedState;
  private Boolean callInProgressState;
  private Boolean roamingState;
  private Boolean smsFullState;
  private Boolean receivedSignalStrengthState;

  public IndicatorControlCommand(final AtCommander atCommander) {
    super(COMMAND_INDICATOR_CONTROL, atCommander);
  }

  public IndicatorControlCommand(final AtCommander atCommander, final boolean batteryChargeLevelState, final Boolean signalQualityState,
                                 final boolean serviceAvailabilityState, final boolean sounderActivityState, final boolean messageReceivedState,
                                 final boolean callInProgressState,
                                 final boolean roamingState, final boolean smsFullState, final boolean receivedSignalStrengthState) {
    super(COMMAND_INDICATOR_CONTROL, atCommander);
    this.batteryChargeLevelState = batteryChargeLevelState;
    this.signalQualityState = signalQualityState;
    this.serviceAvailabilityState = serviceAvailabilityState;
    this.sounderActivityState = sounderActivityState;
    this.messageReceivedState = messageReceivedState;
    this.callInProgressState = callInProgressState;
    this.roamingState = roamingState;
    this.smsFullState = smsFullState;
    this.receivedSignalStrengthState = receivedSignalStrengthState;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_INDICATOR_CONTROL);
      sb.append(EQUAL);
      sb.append(oneOrZero(batteryChargeLevelState));
      if (signalQualityState != null) {
        sb.append(COMMA);
        sb.append(oneOrZero(signalQualityState));
        if (serviceAvailabilityState != null) {
          sb.append(COMMA);
          sb.append(oneOrZero(serviceAvailabilityState));
          if (sounderActivityState != null) {
            sb.append(COMMA);
            sb.append(oneOrZero(sounderActivityState));
            if (messageReceivedState != null) {
              sb.append(COMMA);
              sb.append(oneOrZero(messageReceivedState));
              if (callInProgressState != null) {
                sb.append(COMMA);
                sb.append(oneOrZero(callInProgressState));
                if (roamingState != null) {
                  sb.append(COMMA);
                  sb.append(oneOrZero(roamingState));
                  if (smsFullState != null) {
                    sb.append(COMMA);
                    sb.append(oneOrZero(smsFullState));
                    if (receivedSignalStrengthState != null) {
                      sb.append(COMMA);
                      sb.append(oneOrZero(receivedSignalStrengthState));
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

  public IndicatorControlResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_INDICATOR_CONTROL);
      sb.append(QUERY);
      return new IndicatorControlResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
