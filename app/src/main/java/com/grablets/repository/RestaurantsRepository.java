package com.grablets.repository;

import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.RestaurantsDao;
import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMenuItem;

import java.util.ArrayList;
import java.util.List;

import rx.Completable;
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

  public Completable saveRestaurants(List<DbRestaurant> restaurants) {
    List<DbRestaurantMenuItem> restaurantMenuItems = new ArrayList<>();
    for (DbRestaurant dbRestaurant : restaurants) {
      restaurantMenuItems.addAll(dbRestaurant.getDbRestaurantMenuItems());
    }

    return restaurantMenuItemDao.insert(restaurantMenuItems)
        .startWith(restaurantsDao.insert(restaurants));
  }

  public Completable deleteRestaurants() {
    return restaurantMenuItemDao.delete().startWith(restaurantsDao.delete());
  }
}
