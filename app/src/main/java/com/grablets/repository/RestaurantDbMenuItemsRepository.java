package com.grablets.repository;

import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.model.DbRestaurantMenuItem;

import java.util.List;
import java.util.Set;

import rx.Observable;

public class RestaurantDbMenuItemsRepository {

  private final RestaurantMenuItemDao restaurantMenuItemDao;

  public RestaurantDbMenuItemsRepository(RestaurantMenuItemDao restaurantMenuItemDao) {
    this.restaurantMenuItemDao = restaurantMenuItemDao;
  }

  public Observable<List<DbRestaurantMenuItem>> getMenuItemsByIds(Set<String> ids){
    return restaurantMenuItemDao.getMenuItemsByIds(ids);
  }

  public Observable<List<DbRestaurantMenuItem>> getMenuItemsByRestaurantId(String restaurantId){
    return restaurantMenuItemDao.getMenuItemsByRestaurantId(restaurantId);
  }
}
