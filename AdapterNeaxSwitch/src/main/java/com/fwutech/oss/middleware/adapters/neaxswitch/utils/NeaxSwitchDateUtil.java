package com.fwutech.oss.middleware.adapters.neaxswitch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.fwutech.oss.middleware.commons.utils.DateUtil.iso8601BasicToDate;

public class NeaxSwitchDateUtil {

    private static final String NEAX_SWITCH_DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat NEAX_SWITCH_DATE = new SimpleDateFormat(NEAX_SWITCH_DATE_FORMAT);

    public NeaxSwitchDateUtil() {
        NEAX_SWITCH_DATE.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static long iso8601BasicToEpochTime(String isoDateAsString) throws ParseException {
        if (isoDateAsString == null) {
            return 0l;
        }
        Date date = iso8601BasicToDate(isoDateAsString);
        return date.getTime() / 1000;
    }

    public static String iso8601BasicToDateV2(String isoDateAsString) throws ParseException {
        if (isoDateAsString == null) {
            return "";
        }
        return NEAX_SWITCH_DATE.format(iso8601BasicToDate(isoDateAsString));
    }
}
