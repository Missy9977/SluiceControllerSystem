package com.sluice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author : missy
 * @date : 2022-01-12 19:26
 */
public class DateLocalUtcUtil {

    private static final Log LOGGER = LogFactory.getLog(DateLocalUtcUtil.class);

    private static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'";

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateLocalUtcUtil() {}

    public static String getThisUTCTime() {
        Date date = new Date();
        return getUTCSDF().format(date);
    }

    public static String localToUTC(String localTimeStr) {
        try {
            Date localDate = getLocalSDF().parse(localTimeStr);
            return getUTCSDF().format(localDate);
        } catch (ParseException e) {
            LOGGER.error("Failed to localToUTC");
            LOGGER.error(e);
        }

        return null;
    }

    public static String utcToLocal(String utcTimeStr) {
        try {
            Date date = getUTCSDF().parse(utcTimeStr);
            return getLocalSDF().format(date);
        } catch (ParseException e) {
            LOGGER.error("Failed to utcToLocal");
            LOGGER.error(e);
        }
        return null;
    }

    private static SimpleDateFormat getLocalSDF() {
        return new SimpleDateFormat(DATE_TIME_FORMAT);
    }

    private static SimpleDateFormat getUTCSDF() {
        SimpleDateFormat utcSDF = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
        utcSDF.setTimeZone(TimeZone.getTimeZone("UTC"));
        return utcSDF;
    }
}