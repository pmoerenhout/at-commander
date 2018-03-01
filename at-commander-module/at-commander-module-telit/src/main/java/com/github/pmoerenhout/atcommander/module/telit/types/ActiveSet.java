package com.github.pmoerenhout.atcommander.module.telit.types;

public class ActiveSet {

  private final int uarfcn;
  private final float psc;
  private final float ecio;

  public ActiveSet(final int uarfcn, final float psc, final float ecio) {
    this.uarfcn = uarfcn;
    this.psc = psc;
    this.ecio = ecio;
  }

  public int getUarfcn() {
    return uarfcn;
  }

  public float getPsc() {
    return psc;
  }

  public float getEcio() {
    return ecio;
  }
}
