package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class HttpConfigurationCommand extends BaseCommand implements Command<BaseResponse> {

  // #HTTPCFG: (0-2),50,(1-65535),(0,1),50,50,(0),(1-65535),(1-5)

  private static final String COMMAND_HTTPCFG = "#HTTPCFG";

  private int profileId;
  private String serverAddress;
  private Integer serverPort;
  private Integer authenticationType;
  private String username;
  private String password;
  private Boolean sslEnabled;
  private Integer timeout;
  private Integer cid;
  // not supported in HE910 12.00.004
  private Integer packetSize;

  public HttpConfigurationCommand(final AtCommander atCommander, final int profileId) {
    super(COMMAND_HTTPCFG, atCommander);
    this.profileId = profileId;
  }

  public HttpConfigurationCommand(final AtCommander atCommander, final int profileId, final String serverAddress,
                                  final Integer serverPort, final Integer authenticationType, final String username,
                                  final String password, final Boolean sslEnabled, final Integer timeout,
                                  final Integer cid) {
    super(COMMAND_HTTPCFG, atCommander);
    this.profileId = profileId;
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
    this.authenticationType = authenticationType;
    this.username = username;
    this.password = password;
    this.sslEnabled = sslEnabled;
    this.timeout = timeout;
    this.cid = cid;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_HTTPCFG);
      sb.append(EQUAL);
      sb.append(String.valueOf(profileId));
      if (serverAddress != null) {
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(String.valueOf(serverAddress));
        sb.append(DOUBLE_QUOTE);
        if (serverPort != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(serverPort));
          if (authenticationType != null) {
            sb.append(COMMA);
            sb.append(String.valueOf(authenticationType));
            if (username != null) {
              sb.append(COMMA);
              sb.append(DOUBLE_QUOTE);
              sb.append(username);
              sb.append(DOUBLE_QUOTE);
              if (password != null) {
                sb.append(COMMA);
                sb.append(DOUBLE_QUOTE);
                sb.append(password);
                sb.append(DOUBLE_QUOTE);
                if (sslEnabled != null) {
                  sb.append(COMMA);
                  sb.append(sslEnabled ? "1" : "0");
                  if (timeout != null) {
                    sb.append(COMMA);
                    sb.append(String.valueOf(timeout));
                    if (cid != null) {
                      sb.append(COMMA);
                      sb.append(String.valueOf(cid));
                      if (packetSize != null) {
                        sb.append(COMMA);
                        sb.append(String.valueOf(packetSize));
                      }
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

  public void setPacketSize(final Integer packetSize) {
    this.packetSize = packetSize;
  }
}
