package com.grablets.di.module;

import com.grablets.db.BasketItemDao;
import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.RestaurantsDao;
import com.grablets.repository.BasketDbRepository;
import com.grablets.repository.RestaurantDbMenuItemsRepository;
import com.grablets.repository.RestaurantsDbRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  public RestaurantsDbRepository providesRestaurantsRepository(RestaurantsDao restaurantsDao, RestaurantMenuItemDao restaurantMenuItemDao){
    return new RestaurantsDbRepository(restaurantsDao, restaurantMenuItemDao);
  }

  @Provides
  @Singleton
  public BasketDbRepository providesBasketDbRepository(BasketItemDao basketItemDao){
    return new BasketDbRepository(basketItemDao);
  }

  @Provides
  @Singleton
  public RestaurantDbMenuItemsRepository providesRestaurantMenuItemsRepository(RestaurantMenuItemDao restaurantMenuItemDao){
    return new RestaurantDbMenuItemsRepository(restaurantMenuItemDao);
  }
}
