package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.types.FacilityStatus;

public class FacilityLockResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN1 = Pattern.compile("^\\+CLCK: (\\d*)$");
  private final static Pattern PATTERN2 = Pattern.compile("^\\+CLCK: (\\d*),(\\d*)$");

  private FacilityStatus[] facilityStatuses;

  public FacilityLockResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<FacilityStatus> arrayList = new ArrayList<>();
    for (final String line : response.getInformationalText()) {
      final Matcher m1 = PATTERN1.matcher(line);
      if (m1.find()) {
        final int status = Integer.parseInt(m1.group(1));
        arrayList.add(new FacilityStatus(status));
      } else {
        final Matcher m2 = PATTERN2.matcher(line);
        if (m2.find()) {
          final int status = Integer.parseInt(m2.group(1));
          final Integer clazz = Integer.parseInt(m2.group(2));
          arrayList.add(new FacilityStatus(status, clazz));
          facilityStatuses = arrayList.toArray(new FacilityStatus[arrayList.size()]);
          return;
        } else {
          throw createParseException(line);
        }
      }
    }
    facilityStatuses = arrayList.toArray(new FacilityStatus[arrayList.size()]);
    return;
  }

  public FacilityStatus[] getFacilityStatus() {
    return facilityStatuses;
  }
}
