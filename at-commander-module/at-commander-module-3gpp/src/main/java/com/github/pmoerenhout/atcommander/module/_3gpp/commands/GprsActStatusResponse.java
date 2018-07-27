package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.types.GprsAct;

public class GprsActStatusResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CGACT: (\\d*),(\\d*)");

  private GprsAct[] gprsActives;

  public GprsActStatusResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<GprsAct> arrayList = new ArrayList<>();
    for (final String line : response.getInformationalText()) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int cid = Integer.parseInt(m.group(1));
        final boolean active = BooleanUtils.toBoolean(Integer.parseInt(m.group(2)));
        arrayList.add(new GprsAct(cid, active));
      } else {
        throw createParseException(line);
      }
    }
    gprsActives = arrayList.toArray(new GprsAct[arrayList.size()]);
  }

  public GprsAct[] getGprsActives() {
    return gprsActives;
  }
}
