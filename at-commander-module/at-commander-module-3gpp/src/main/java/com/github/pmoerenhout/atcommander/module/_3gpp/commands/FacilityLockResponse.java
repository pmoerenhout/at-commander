package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.types.FacilityStatus;

public class FacilityLockResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^\\+CLCK: (\\d*)(,(\\d*))?$");

  private FacilityStatus[] facilityStatuses;

  public FacilityLockResponse() {
  }

  public FacilityLockResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<FacilityStatus> arrayList = new ArrayList<>();
    for (final String line : response.getInformationalText()) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int status = Integer.parseInt(m.group(1));
        if (m.group(2) != null) {
          final Integer clazz = Integer.valueOf(m.group(3));
          arrayList.add(new FacilityStatus(status, clazz));
        }
        else {
          arrayList.add(new FacilityStatus(status));
        }
      }
      else {
        throw createParseException(line);
      }
    }
    facilityStatuses = arrayList.toArray(new FacilityStatus[arrayList.size()]);
    return;
  }

  public FacilityStatus[] getFacilityStatus() {
    return facilityStatuses;
  }
}
