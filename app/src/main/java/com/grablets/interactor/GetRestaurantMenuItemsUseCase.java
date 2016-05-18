package com.grablets.interactor;

import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.repository.RestaurantDbMenuItemsRepository;

import java.util.List;
import java.util.Set;

import rx.Observable;

public class GetRestaurantMenuItemsUseCase implements UseCaseWithParameter<List<DbRestaurantMenuItem>, Set<String>>{

  private final RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository;

  public GetRestaurantMenuItemsUseCase(RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository) {
    this.restaurantDbMenuItemsRepository = restaurantDbMenuItemsRepository;
  }

  @Override
  public Observable<List<DbRestaurantMenuItem>> execute(Set<String> ids) {
    return restaurantDbMenuItemsRepository.getMenuItemsByIds(ids);
  }
}
