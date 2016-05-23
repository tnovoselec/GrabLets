package com.grablets.business;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class PreferenceAccessor {

  private static final String ACTIVE_MENU_ITEM = "active_menu_item";
  private static final String NOTIFICATIONS_ENABLED = "notifications_enabled";
  private static final String NOTIFICATIONS_TIME = "notifications_time";

  private static final String FAVOURITE_RESTAURANTS = "favourite_restaurants";

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

  public void setNotificationsEnabled(boolean enabled){
    set(NOTIFICATIONS_ENABLED, enabled);
  }

  public boolean areNotificationsEnabled(){
    return getBoolean(NOTIFICATIONS_ENABLED);
  }

  public void setNotificationsTime(long notificationsTime){
    set(NOTIFICATIONS_TIME, notificationsTime);
  }

  public long getNotificationsTime(){
    return getLong(NOTIFICATIONS_TIME);
  }

  public void setFavouriteRestaurants(Set<String> restaurantIds){
    edit().putStringSet(FAVOURITE_RESTAURANTS, restaurantIds).apply();
  }

  public Set<String> getFavouriteRestaurants(){
    return sharedPreferences.getStringSet(FAVOURITE_RESTAURANTS, new HashSet<>());
  }

  // Helper methods

  private boolean getBoolean(String key) {
    return sharedPreferences.getBoolean(key, false);
  }

  private int getInt(String key) {
    return sharedPreferences.getInt(key, 0);
  }

  private long getLong(String key) {
    return sharedPreferences.getLong(key, 0);
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

  private void set(String key, long value) {
    edit().putLong(key, value).apply();
  }

  private void set(String key, String value) {
    edit().putString(key, value).apply();
  }

  private SharedPreferences.Editor edit() {
    return sharedPreferences.edit();
  }
}
