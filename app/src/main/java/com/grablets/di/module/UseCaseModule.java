package com.grablets.di.module;

import com.grablets.api.GrabLetsClient;
import com.grablets.interactor.ChangeRestaurantFavouriteStatusUseCase;
import com.grablets.interactor.ClearBasketUseCase;
import com.grablets.interactor.CreateOrderUseCase;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetFavouriteRestaurantsUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsByIdUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.repository.BasketDbRepository;
import com.grablets.repository.FavouriteRestaurantsRepository;
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

  @Provides
  ClearBasketUseCase provideClearBasketUseCase(BasketDbRepository basketDbRepository){
    return new ClearBasketUseCase(basketDbRepository);
  }

  @Provides
  CreateOrderUseCase provideCreateOrderUseCase(){
    return new CreateOrderUseCase();
  }

  @Provides
  GetRestaurantMenuItemsByIdUseCase provideGetRestaurantMenuItemsByIdUseCase(RestaurantDbMenuItemsRepository restaurantDbMenuItemsRepository){
    return new GetRestaurantMenuItemsByIdUseCase(restaurantDbMenuItemsRepository);
  }

  @Provides
  ChangeRestaurantFavouriteStatusUseCase provideChangeRestaurantFavouriteStatusUseCase(FavouriteRestaurantsRepository favouriteRestaurantsRepository){
    return new ChangeRestaurantFavouriteStatusUseCase(favouriteRestaurantsRepository);
  }

  @Provides
  GetFavouriteRestaurantsUseCase provideGetFavouriteRestaurantsUseCase(FavouriteRestaurantsRepository favouriteRestaurantsRepository){
    return new GetFavouriteRestaurantsUseCase(favouriteRestaurantsRepository);
  }
}
