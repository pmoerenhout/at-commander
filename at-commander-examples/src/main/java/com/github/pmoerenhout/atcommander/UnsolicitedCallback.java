package com.github.pmoerenhout.atcommander;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.GprsNetworkRegistrationUnsolicited;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnsolicitedCallback implements UnsolicitedResponseCallback {

  public void unsolicited(final UnsolicitedResponse response) {
    if (response instanceof GprsNetworkRegistrationUnsolicited) {
      final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = (GprsNetworkRegistrationUnsolicited) response;
      log.info("Unsolicited GPRS: state:{} lac:{} cid:{}",
          gprsNetworkRegistrationUnsolicited.getRegistrationState(),
          gprsNetworkRegistrationUnsolicited.getLac(),
          gprsNetworkRegistrationUnsolicited.getCellId());
      return;
    }
    log.info("Received unsolicited response: {}", response);
  }
}
