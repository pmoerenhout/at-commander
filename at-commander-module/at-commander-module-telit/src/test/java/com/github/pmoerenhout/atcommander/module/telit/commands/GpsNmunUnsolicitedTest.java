package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.util.CompassPoint;
import net.sf.marineapi.nmea.util.Datum;
import net.sf.marineapi.nmea.util.GpsFixQuality;
import net.sf.marineapi.nmea.util.Position;
import net.sf.marineapi.nmea.util.Time;
import net.sf.marineapi.nmea.util.Units;

public class GpsNmunUnsolicitedTest {

  @Test
  public void test_gps_gga() {
    final GpsNmunUnsolicited gpsNmunUnsolicited = new GpsNmunUnsolicited();
    gpsNmunUnsolicited.parseUnsolicited(Collections.singletonList("$GPSNMUN: $GPGGA,210423.000,5204.8316,N,00451.4745,E,1,08,1.50,57.0,M,,M,,0000*47"));

    final Sentence sentence = gpsNmunUnsolicited.getSentence();

    assertEquals("GGA", sentence.getSentenceId());

    final GGASentence gga = (GGASentence) sentence;

    assertEquals(new Time("210423.000"), gga.getTime());
    final Position pos = gga.getPosition();
    assertEquals(52.080526666666664, pos.getLatitude(), 0);
    assertEquals(CompassPoint.NORTH, pos.getLatitudeHemisphere());
    assertEquals(4.8579083333333335, pos.getLongitude(), 0);
    assertEquals(CompassPoint.EAST, pos.getLongitudeHemisphere());
    assertEquals(57.0, pos.getAltitude(), 0);
    assertEquals(Datum.WGS84, pos.getDatum());
    assertEquals(57.0, gga.getAltitude(), 0);
    assertEquals(Units.METER, gga.getAltitudeUnits());
    assertEquals("0000", gga.getDgpsStationId());
    assertEquals(1.5, gga.getHorizontalDOP(), 0);
    assertEquals(8, gga.getSatelliteCount());
    assertEquals(GpsFixQuality.NORMAL, gga.getFixQuality());
    assertEquals(Units.METER, gga.getGeoidalHeightUnits());
  }

  @Test
  public void test_gps_gga_no_fix() {
    final GpsNmunUnsolicited gpsNmunUnsolicited = new GpsNmunUnsolicited();
    gpsNmunUnsolicited.parseUnsolicited(Collections.singletonList("$GPSNMUN: $GPGGA,,,,,,0,,,,M,,M,,*66"));

    final Sentence sentence = gpsNmunUnsolicited.getSentence();

    assertEquals("GGA", sentence.getSentenceId());

    final GGASentence gga = (GGASentence) sentence;

    assertEquals(GpsFixQuality.INVALID, gga.getFixQuality());
    assertEquals(Units.METER, gga.getAltitudeUnits());
    assertEquals(Units.METER, gga.getGeoidalHeightUnits());
  }
}