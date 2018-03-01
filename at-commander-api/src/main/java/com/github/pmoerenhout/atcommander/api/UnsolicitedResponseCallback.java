package com.github.pmoerenhout.atcommander.api;

public interface UnsolicitedResponseCallback {
  // This is just a regular method so it can return something or
  // take arguments if you like.
  void unsolicited(UnsolicitedResponse reponse);
}