package org.atcommander.module.telit.commands.types;

import org.atcommander.module.telit.enums.GsmBand;
import org.atcommander.module.v250.enums.UmtsBand;

public class Band {

  private GsmBand gsmBand;
  private UmtsBand umtsBand;

  public Band(final GsmBand gsmBand, final UmtsBand umtsBand) {
    this.gsmBand = gsmBand;
    this.umtsBand = umtsBand;
  }

  public GsmBand getGsmBand() {
    return gsmBand;
  }

  public void setGsmBand(final GsmBand gsmBand) {
    this.gsmBand = gsmBand;
  }

  public UmtsBand getUmtsBand() {
    return umtsBand;
  }

  public void setUmtsBand(final UmtsBand umtsBand) {
    this.umtsBand = umtsBand;
  }
}
