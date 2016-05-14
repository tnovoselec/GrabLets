package com.grablets.business;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferenceAccessor {

  private static final String ACTIVE_MENU_ITEM = "active_menu_item";

  private final SharedPreferences sharedPreferences;

  @Inject
  public PreferenceAccessor(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public void setActiveMenuItem(int menuItemId) {
    set(ACTIVE_MENU_ITEM, menuItemId);
  }

  public int getActiveMenuItem() {
    return getInt(ACTIVE_MENU_ITEM);
  }

  private boolean getBoolean(String key) {
    return sharedPreferences.getBoolean(key, false);
  }

  private int getInt(String key) {
    return sharedPreferences.getInt(key, 0);
  }

  private String getString(String key) {
    return sharedPreferences.getString(key, null);
  }

  private void set(String key, boolean value) {
    edit().putBoolean(key, value).apply();
  }

  private void set(String key, int value) {
    edit().putInt(key, value).apply();
  }

  private void set(String key, String value) {
    edit().putString(key, value).apply();
  }

  private SharedPreferences.Editor edit() {
    return sharedPreferences.edit();
  }
}
