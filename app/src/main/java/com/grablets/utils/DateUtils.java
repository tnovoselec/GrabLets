package com.grablets.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

  private static final String FORMAT_FULL_DATE_TIME = "MMM dd, HH:mm";

  public static String formatMyOrderDate(long timestamp){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_FULL_DATE_TIME, new Locale("hr_HR"));
    return simpleDateFormat.format(new Date(timestamp));
  }
}
