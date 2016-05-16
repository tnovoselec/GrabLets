package com.grablets.business;

import com.grablets.api.model.ApiDailyMenuItem;
import com.grablets.api.model.ApiRestaurant;
import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMenuItem;

import java.util.ArrayList;
import java.util.List;

public class ApiToDbConverter {

  public static List<DbRestaurant> fromRestaurants(List<ApiRestaurant> restaurants) {
    List<DbRestaurant> dbRestaurants = new ArrayList<>();

    for (ApiRestaurant apiRestaurant : restaurants) {
      DbRestaurant dbRestaurant = new DbRestaurant(
          apiRestaurant.id,
          apiRestaurant.title,
          apiRestaurant.description,
          apiRestaurant.imageUrl
      );
      dbRestaurant.setDbRestaurantMenuItems(fromRestaurantMenuItem(apiRestaurant.menu, apiRestaurant.id));
      dbRestaurants.add(dbRestaurant);
    }

    return dbRestaurants;
  }

  public static List<DbRestaurantMenuItem> fromRestaurantMenuItem(List<ApiDailyMenuItem> dailyMenuItems, String restaurantId) {
    List<DbRestaurantMenuItem> dbRestaurantMenuItems = new ArrayList<>();

    for (ApiDailyMenuItem apiDailyMenuItem : dailyMenuItems) {
      DbRestaurantMenuItem dbRestaurantMenuItem = new DbRestaurantMenuItem(
          apiDailyMenuItem.id,
          restaurantId,
          apiDailyMenuItem.title,
          apiDailyMenuItem.description,
          apiDailyMenuItem.imageUrl
      );
      dbRestaurantMenuItems.add(dbRestaurantMenuItem);
    }

    return dbRestaurantMenuItems;
  }
}
