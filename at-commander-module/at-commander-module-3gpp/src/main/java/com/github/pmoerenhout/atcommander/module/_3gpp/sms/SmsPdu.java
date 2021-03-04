package com.github.pmoerenhout.atcommander.module._3gpp.sms;

import java.nio.charset.Charset;
import java.util.Formatter;

import lombok.extern.slf4j.Slf4j;
import net.freeutils.charset.gsm.CCGSMCharset;
import net.freeutils.charset.gsm.CCPackedGSMCharset;

@Slf4j
public class SmsPdu {

  private static final Charset CHARSET_GSM_PACKED = new CCPackedGSMCharset();
  private static final Charset CHARSET_GSM = new CCGSMCharset();

  private String smsc;
  private String destination;
  private byte[] header;
  private byte[] data;
  private String text;
  private byte pid;
  private byte dcs;

  private int length;

  public SmsPdu() {
    this.smsc = null;
    this.length = 0;
  }

  public String create() {
    final String smscPart = getSmscPart();
    final String pduPart = getPduPart();
    final String complete = smscPart + pduPart;
    log.debug("SMS-PDU: {}", complete);
    log.debug("SMS-PDU: {} octets", length);
    return complete;
  }

  public String getSmscPart() {
    String pdu = smscAddress(smsc);
    log.debug("SMSC part bytes: {}", pdu);
    return pdu;
  }

  public int getMessageLength() {
    return length;
  }

  public String getPduPart() {
    String ud = null;
    int l = 0;

    // TP-SRR / TP-UDHI / TP-RP / TP-VDF(2) / TP-RD / TP-MTI(2)
    // 0-1-0-00-0-01
    String pdu = (header.length != 0 ? "41" : "01"); // SMS-SUBMIT
    l++;

    // WITH/WITHOUT UDH
    pdu += "00"; // TP-Message-Reference
    l++;

    // // TP-Destination-Address
    final String addressPart = address(destination);
    pdu += addressPart;
    l += (addressPart.length() / 2);

    // TP-PID
    pdu += bytesToHexString(pid);
    l++;

    // TP-DCS
    pdu += bytesToHexString(dcs);
    l++;
    // Optional TP_Validity-Period
    // pdu += "AA"; // TP-VALIDITY

    /* Create UD */
    if (header.length != 0) {
      ud = String.format("%02X", header.length) + bytesToHexString(header);
      l += header.length;
    } else {
      ud = "";
    }
    switch (dcs) {
      case 0x00:
        final byte[] packed = text.getBytes(CHARSET_GSM_PACKED);
        final byte[] unpacked = text.getBytes(CHARSET_GSM);
        final int septets = text.getBytes(CHARSET_GSM).length;
        pdu += String.format("%02X", unpacked.length); // TP-UDL
        l++;
        log.debug("packed   {}", bytesToHexString(text.getBytes(CHARSET_GSM_PACKED)));
        log.debug("unpacked {}", bytesToHexString(text.getBytes(CHARSET_GSM)));
        log.debug("packed.length  {}", packed.length);
        log.debug("septets        {}", septets);
        //LOG("unpacked {}", bytesToHexString(charsetGsmPacked));

        ud += bytesToHexString(packed);
        pdu += ud;
        l += packed.length;
        //l += (septets);
        break;

      default:
        pdu += String.format("%02X", ud.length() / 2); // TP-UDL
        l++;

        ud += bytesToHexString(data);
        pdu += ud;
        l += data.length;
    }
    length = l;

    log.debug("PDU part bytes: {} length: {}", pdu, length);
    return pdu;
  }

  private String smscAddress(String s) {
    StringBuilder sb = new StringBuilder();
    if (s == null || s.length() == 0) {
      return "00";
    }
    // int semiOctets = s.length();
    if (s.length() % 2 == 1) {
      s += "F";
    }
    for (int i = 0; i < s.length(); i += 2) {
      sb.append(s.charAt(i + 1));
      sb.append(s.charAt(i));
    }
    return String.format("%02X", 1 + (s.length() / 2)) + "91"
        + sb.toString();
  }

  private String address(final String address) {
    String s = address;
    StringBuilder sb = new StringBuilder();
    int semiOctets = s.length();
    if (s.length() % 2 == 1) {
      s += "F";
    }
    for (int i = 0; i < s.length(); i += 2) {
      sb.append(s.charAt(i + 1));
      sb.append(s.charAt(i));
    }
    return String.format("%02X", semiOctets) + "91" + sb.toString();
  }

  private String reverse(String s) {
    StringBuilder sb = new StringBuilder();
    if (s.length() % 2 == 1) {
      s += "F";
    }
    for (int i = 0; i < s.length(); i += 2) {
      sb.append(s.charAt(i + 1));
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  private String bytesToHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder(bytes.length * 2);

    Formatter formatter = new Formatter(sb);
    for (byte b : bytes) {
      formatter.format("%02X", b);
    }
    return sb.toString();
  }

  private String bytesToHexString(byte b) {
    StringBuilder sb = new StringBuilder(2);
    Formatter formatter = new Formatter(sb);
    formatter.format("%02X", b);
    return sb.toString();
  }

  public String getSmsc() {
    return smsc;
  }

  public void setSmsc(final String smsc) {
    this.smsc = smsc;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(final String destination) {
    this.destination = destination;
  }

  public byte[] getHeader() {
    return header;
  }

  public void setHeader(final byte[] header) {
    this.header = header;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(final byte[] data) {
    this.data = data;
  }

  public byte getPid() {
    return pid;
  }

  public void setPid(final byte pid) {
    this.pid = pid;
  }

  public byte getDcs() {
    return dcs;
  }

  public void setDcs(final byte dcs) {
    this.dcs = dcs;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }
}
