package com.grablets.di.module;

import com.grablets.api.GrabLetsClient;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.repository.BasketDbRepository;
import com.grablets.repository.RestaurantDbMenuItemsRepository;
import com.grablets.repository.RestaurantsDbRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

  @Provides
  GetRestaurantsUseCase provideGetRestaurantsUseCase(GrabLetsClient grabLetsClient, RestaurantsDbRepository restaurantsDbRepository) {
    return new GetRestaurantsUseCase(grabLetsClient, restaurantsDbRepository);
  }

  @Provides
  GetBasketUseCase provideGetBasketUseCase(BasketDbRepository basketDbRepository) {
    return new GetBasketUseCase(basketDbRepository);
  }

  @Provides
  GetRestaurantMenuItemsUseCase provideGetRestaurantMenuItemsUseCase(RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository) {
    return new GetRestaurantMenuItemsUseCase(restaurantDbMenuItemsRepository);
  }
}
