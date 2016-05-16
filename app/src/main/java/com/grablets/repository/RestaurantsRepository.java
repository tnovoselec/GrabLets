package com.grablets.repository;

import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.RestaurantsDao;
import com.grablets.db.model.DbRestaurant;

import java.util.List;

import rx.Observable;

public class RestaurantsRepository {

  private final RestaurantsDao restaurantsDao;
  private final RestaurantMenuItemDao restaurantMenuItemDao;

  public RestaurantsRepository(RestaurantsDao restaurantsDao, RestaurantMenuItemDao restaurantMenuItemDao) {
    this.restaurantsDao = restaurantsDao;
    this.restaurantMenuItemDao = restaurantMenuItemDao;
  }

  public Observable<List<DbRestaurant>> getRestaurants() {
    return restaurantsDao.getRestaurants();
  }

  public void saveRestaurants(List<DbRestaurant> restaurants) {
    restaurantsDao.insert(restaurants);
    for (DbRestaurant dbRestaurant : restaurants) {
      restaurantMenuItemDao.insert(dbRestaurant.getDbRestaurantMenuItems());
    }
  }
}
