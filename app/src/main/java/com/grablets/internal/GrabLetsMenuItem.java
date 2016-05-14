package com.grablets.internal;

public enum GrabLetsMenuItem {
  DAILY_MENU(0),
  RESTAURANT_LIST(1),
  MY_ORDERS(2),
  MY_RESTAURANTS(3),
  NEWS(4),
  SETTINGS(5);

  public final int itemId;

  GrabLetsMenuItem(int itemId) {
    this.itemId = itemId;
  }
}
