package com.github.pmoerenhout.atcommander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponseCallback;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.GprsNetworkRegistrationUnsolicited;

public class UnsolicitedCallback implements UnsolicitedResponseCallback {

  private static final Logger LOG = LoggerFactory.getLogger(UnsolicitedCallback.class);

  public void unsolicited(final UnsolicitedResponse response){
    if (response instanceof GprsNetworkRegistrationUnsolicited){
      final GprsNetworkRegistrationUnsolicited gprsNetworkRegistrationUnsolicited = (GprsNetworkRegistrationUnsolicited)response;
      LOG.info("Unsolicited GPRS: state:{} lac:{} cid:{}",
          gprsNetworkRegistrationUnsolicited.getRegistrationState(),
          gprsNetworkRegistrationUnsolicited.getLac(),
          gprsNetworkRegistrationUnsolicited.getCellId());
      return;
    }
    LOG.info("Received unsolicited response: {}", response);
  }
}
