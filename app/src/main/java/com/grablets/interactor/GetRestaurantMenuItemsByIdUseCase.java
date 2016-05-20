package com.grablets.interactor;

import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.repository.RestaurantDbMenuItemsRepository;

import java.util.List;

import rx.Observable;

public class GetRestaurantMenuItemsByIdUseCase implements UseCaseWithParameter<List<DbRestaurantMenuItem>, String> {

  private final RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository;

  public GetRestaurantMenuItemsByIdUseCase(RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository) {
    this.restaurantDbMenuItemsRepository = restaurantDbMenuItemsRepository;
  }

  @Override
  public Observable<List<DbRestaurantMenuItem>> execute(String restaurantId) {
    return restaurantDbMenuItemsRepository.getMenuItemsByRestaurantId(restaurantId);
  }
}
