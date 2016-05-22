package com.grablets.utils;

import java.text.DecimalFormat;

public class FormatUtils {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  public static String formatPrice(double value) {
    return DECIMAL_FORMAT.format(value);
  }
}
