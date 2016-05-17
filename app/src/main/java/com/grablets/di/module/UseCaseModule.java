package com.grablets.di.module;

import com.grablets.api.GrabLetsClient;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.repository.RestaurantsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

  @Provides
  GetRestaurantsUseCase provideGetRestaurantsUseCase(GrabLetsClient grabLetsClient, RestaurantsRepository restaurantsRepository){
    return new GetRestaurantsUseCase(grabLetsClient, restaurantsRepository);
  }
}
