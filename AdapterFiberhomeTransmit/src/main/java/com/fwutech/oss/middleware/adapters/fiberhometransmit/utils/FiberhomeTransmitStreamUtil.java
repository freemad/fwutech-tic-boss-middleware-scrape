package com.fwutech.oss.middleware.adapters.fiberhometransmit.utils;

import mtnm.tmforum.org.notifications.PerceivedSeverity_T;

import java.util.stream.Stream;

public class FiberhomeTransmitStreamUtil {

    public static PerceivedSeverity_T[] toPerceivedSeverityArray(String param, String delimiter) {
        String[] excludeSeverityValues = param != null && !param.trim().equals("") ? param.split(delimiter) : new String[0];
        return excludeSeverityValues.length > 0 && excludeSeverityValues[0].length() > 0
                ? Stream.of(excludeSeverityValues)
                .map(cause -> PerceivedSeverity_T.from_int(Integer.parseInt(cause)))
                .toArray(PerceivedSeverity_T[]::new)
                : new PerceivedSeverity_T[0];
    }

}
