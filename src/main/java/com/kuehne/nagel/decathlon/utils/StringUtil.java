package com.kuehne.nagel.decathlon.utils;

public class StringUtil {

    private static final String SEPARATOR_MINUTE_SECOND = ":";

    public static String convertFromMinuteToSecond(String minute) {
        String[] splittedValue = minute.split(SEPARATOR_MINUTE_SECOND);
        if (splittedValue.length == 1) {
            return splittedValue[0];
        }

        if (splittedValue.length == 3) {
            throw new DecathlonException("input format should be mm:ss.SSS");
        }

        double second = Double.parseDouble(splittedValue[0]) * 60;
        return String.valueOf(second + Double.parseDouble(splittedValue[1]));
    }

    public static boolean isEmpty(String line) {
        if (line == null || line.isEmpty()) {
            return true;
        }
        return false;
    }
}
