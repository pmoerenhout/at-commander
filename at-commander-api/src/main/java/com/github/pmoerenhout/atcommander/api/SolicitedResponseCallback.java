package com.github.pmoerenhout.atcommander.api;

public interface SolicitedResponseCallback {
  // This is just a regular method so it can return something or
  // take arguments if you like.
  void solicited(String line);
}