package org.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.module.telit.types.GprsDataVolume;
import org.atcommander.basic.commands.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;

public class GprsDataVolumeResponse extends BaseResponse implements Response {

  // GDATAVOL: <cidn>,<totn>,<sentn>,<receivedn>

  private static final Pattern PATTERN = Pattern.compile("^#GDATAVOL: (\\d*),(\\d*),(\\d*),(\\d*)");

  private static final Logger LOG = LoggerFactory.getLogger(GprsDataVolumeResponse.class);

  private GprsDataVolume[] gprsDataVolumes;

  public GprsDataVolumeResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<GprsDataVolume> arrayList = new ArrayList<>();
    for (final String line : response.getInformationalText()) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int cid = Integer.parseInt(m.group(1));
        final int total = Integer.parseInt(m.group(2));
        final int sent = Integer.parseInt(m.group(3));
        final int received = Integer.parseInt(m.group(4));
        if (total != (sent + received)) {
          LOG.warn("Total {} does not equal sent {} and received {}", total, sent, received);
        }
        arrayList.add(new GprsDataVolume(cid, total, sent, received));
      } else {
        throw createParseException(response);
      }
    }
    gprsDataVolumes = arrayList.toArray(new GprsDataVolume[arrayList.size()]);
  }

  public GprsDataVolume[] getGprsDataVolumes() {
    return gprsDataVolumes;
  }
}
