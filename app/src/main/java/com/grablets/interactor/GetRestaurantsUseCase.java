package com.grablets.interactor;

import com.grablets.api.GrabLetsClient;
import com.grablets.business.ApiToDbConverter;
import com.grablets.db.model.DbRestaurant;
import com.grablets.repository.RestaurantsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;

public class GetRestaurantsUseCase implements UseCase<List<DbRestaurant>> {

  private final GrabLetsClient grabLetsClient;
  private final RestaurantsRepository restaurantsRepository;

  @Inject
  public GetRestaurantsUseCase(GrabLetsClient grabLetsClient, RestaurantsRepository restaurantsRepository) {
    this.grabLetsClient = grabLetsClient;
    this.restaurantsRepository = restaurantsRepository;
  }

  @Override
  public Observable<List<DbRestaurant>> execute() {
    return Observable.defer(grabLetsClient::getRestaurants)
        .map(ApiToDbConverter::fromRestaurants)
        .flatMap(dbRestaurants -> persistRestaurants(dbRestaurants)
            .andThen(Observable.just(dbRestaurants)));

  }

  private Completable persistRestaurants(List<DbRestaurant> restaurants) {
    return restaurantsRepository.deleteRestaurants().endWith(
        restaurantsRepository.saveRestaurants(restaurants));
  }
}
