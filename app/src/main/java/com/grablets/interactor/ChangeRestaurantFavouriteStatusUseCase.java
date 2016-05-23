package com.grablets.interactor;

import com.grablets.repository.FavouriteRestaurantsRepository;

import rx.Completable;

public class ChangeRestaurantFavouriteStatusUseCase {

  private final FavouriteRestaurantsRepository favouriteRestaurantsRepository;

  public ChangeRestaurantFavouriteStatusUseCase(FavouriteRestaurantsRepository favouriteRestaurantsRepository) {
    this.favouriteRestaurantsRepository = favouriteRestaurantsRepository;
  }

  public Completable execute(String restaurantId, boolean isFavourite) {
    return favouriteRestaurantsRepository.setRestaurantFavouriteStatus(restaurantId, isFavourite);
  }
}
