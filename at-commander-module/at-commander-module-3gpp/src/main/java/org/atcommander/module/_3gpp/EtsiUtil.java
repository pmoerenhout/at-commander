package org.atcommander.module._3gpp;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import net.freeutils.charset.gsm.CCGSMCharset;

public class EtsiUtil {

  public static Charset toJavaCharset(final String characterSet) {
    if (characterSet == null){
      return Charset.defaultCharset();
    }
    switch (characterSet) {
      case "8859-1":
        return StandardCharsets.ISO_8859_1;
      case "GSM":
        return new CCGSMCharset();
      case "IRA":
        return StandardCharsets.US_ASCII;
      case "UCS2":
        return StandardCharsets.UTF_16BE;
      case "HEX":
        return StandardCharsets.ISO_8859_1;
      case "PCCP437":
        return Charset.forName("IBM437");
      default:
        throw new IllegalArgumentException("Illegal charset " + characterSet);
    }
  }
}
