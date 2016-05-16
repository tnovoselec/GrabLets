package com.grablets.di.module;

import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.RestaurantsDao;
import com.grablets.repository.RestaurantsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  public RestaurantsRepository providesRestaurantsRepository(RestaurantsDao restaurantsDao, RestaurantMenuItemDao restaurantMenuItemDao){
    return new RestaurantsRepository(restaurantsDao, restaurantMenuItemDao);
  }
}
