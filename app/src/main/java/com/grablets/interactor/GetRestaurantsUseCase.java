package com.grablets.interactor;

import com.grablets.api.GrabLetsClient;
import com.grablets.business.ApiToDbConverter;
import com.grablets.db.model.DbRestaurant;
import com.grablets.repository.RestaurantsDbRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;

public class GetRestaurantsUseCase implements UseCase<List<DbRestaurant>> {

  private final GrabLetsClient grabLetsClient;
  private final RestaurantsDbRepository restaurantsDbRepository;

  @Inject
  public GetRestaurantsUseCase(GrabLetsClient grabLetsClient, RestaurantsDbRepository restaurantsDbRepository) {
    this.grabLetsClient = grabLetsClient;
    this.restaurantsDbRepository = restaurantsDbRepository;
  }

  @Override
  public Observable<List<DbRestaurant>> execute() {
    return Observable.defer(grabLetsClient::getRestaurants)
        .map(ApiToDbConverter::fromRestaurants)
        .flatMap(dbRestaurants -> persistRestaurants(dbRestaurants)
            .andThen(Observable.just(dbRestaurants)));

  }

  private Completable persistRestaurants(List<DbRestaurant> restaurants) {
    return restaurantsDbRepository.deleteRestaurants().endWith(
        restaurantsDbRepository.saveRestaurants(restaurants));
  }
}
