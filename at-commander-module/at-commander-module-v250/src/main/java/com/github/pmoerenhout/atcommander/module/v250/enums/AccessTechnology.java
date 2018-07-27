package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum AccessTechnology {
  // http://www.etsi.org/deliver/etsi_ts/127000_127099/127007/08.03.00_60/ts_127007v080300p.pdf
  // http://www.etsi.org/deliver/etsi_ts/127000_127099/127007/10.03.00_60/ts_127007v100300p.pdf

  GSM(0),
  GSM_COMPACT(1),
  UTRAN(2),
  GSM_EGPRS(3),
  UTRAN_HSDPA(4),
  UTRAN_HSUPA(5),
  UTRAN_HSDPA_HSUPA(6),
  E_UTRAN(7),
  EC_GSM_IOT(8),
  E_UTRAN_NB_S1(9),
  E_UTRA_5GCN(10),
  NR_5GCN(11),
  NR_EPS_CORE(12);

  private final int value;

  AccessTechnology(final int value) {
    this.value = value;
  }

  public static AccessTechnology fromInt(final int response) {
    for (final AccessTechnology s : AccessTechnology.values()) {
      if (s.value() == response) {
        return s;
      }
    }
    return null;
  }

  public int value() {
    return value;
  }
}
