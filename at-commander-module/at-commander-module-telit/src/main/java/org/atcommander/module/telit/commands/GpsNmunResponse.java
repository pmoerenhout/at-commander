package org.atcommander.module.telit.commands;

import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.atcommander.basic.commands.BaseResponse;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.Sentence;

public class GpsNmunResponse extends BaseResponse implements UnsolicitedResponse {

  //$GPSNMUN: $GPGGA,142917.000,5049.5326,N,00555.9750,E,1,04,3.25,192.0,M,,M,,0000*74
  //$GPSNMUN: $GPGGA,,,,,,0,,,,M,,M,,*66
  //$GPSNMUN: $GPGLL,5204.8316,N,00451.4745,E,210421.000,A,A*50
  //$GPSNMUN: $GPGGA,,,,,,0,,,,M,,M,,*66

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\$GPSNMUN: (.*)$");

  private static final Logger LOG = LoggerFactory.getLogger(GpsNmunResponse.class);
  private static final SentenceFactory sentenceFactory = SentenceFactory.getInstance();

  private Sentence sentence;

  public GpsNmunResponse(final String line) {
    parse(line);
  }

  public void parse(final String line) {
    final String nmea = line.substring(10);
    sentence = sentenceFactory.createParser(nmea);
    if (!sentence.isValid()) {
      LOG.warn("The NMEA string '{}' is invalid", nmea);
      throw createParseException(line);
    }
    if (sentence.isProprietary()) {
      LOG.warn("The NMEA string '{}' is proprietary", nmea);
      throw createParseException(line);
    }
    LOG.debug("NMEA: '{}'", nmea);
  }

  public Sentence getSentence() {
    return sentence;
  }
}
