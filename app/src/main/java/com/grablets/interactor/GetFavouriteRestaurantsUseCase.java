package com.grablets.interactor;

import com.grablets.repository.FavouriteRestaurantsRepository;

import java.util.Set;

import rx.Observable;

public class GetFavouriteRestaurantsUseCase {

  private final FavouriteRestaurantsRepository favouriteRestaurantsRepository;

  public GetFavouriteRestaurantsUseCase(FavouriteRestaurantsRepository favouriteRestaurantsRepository) {
    this.favouriteRestaurantsRepository = favouriteRestaurantsRepository;
  }

  public Observable<Set<String>> execute() {
    return favouriteRestaurantsRepository.getFavouriteRestaurants();
  }
}
