package com.grablets.repository;

import com.grablets.business.PreferenceAccessor;

import java.util.Set;

import rx.Completable;
import rx.Observable;

public class FavouriteRestaurantsRepository {

  private final PreferenceAccessor preferenceAccessor;

  public FavouriteRestaurantsRepository(PreferenceAccessor preferenceAccessor) {
    this.preferenceAccessor = preferenceAccessor;
  }

  public Observable<Set<String>> getFavouriteRestaurants(){
    return Observable.just(preferenceAccessor.getFavouriteRestaurants());
  }

  public Completable setRestaurantFavouriteStatus(String restaurantId, boolean isFavourite){
    Set<String> favouriteRestaurants = preferenceAccessor.getFavouriteRestaurants();
    if (isFavourite){
      favouriteRestaurants.add(restaurantId);
    }else {
      favouriteRestaurants.remove(restaurantId);
    }
    return Completable.complete();
  }
}
