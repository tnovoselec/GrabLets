package com.grablets.mvp.presenter;

import com.grablets.api.GrabLetsClient;
import com.grablets.business.ApiToDbConverter;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.db.model.DbRestaurant;
import com.grablets.mvp.RestaurantsMvp;
import com.grablets.repository.RestaurantsRepository;
import com.grablets.viewmodel.RestaurantsViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class RestaurantsPresenter extends SubscribingPresenter<RestaurantsMvp.View> implements RestaurantsMvp.Presenter {

  private final GrabLetsClient grabLetsClient;
  private final RestaurantsRepository restaurantsRepository;

  @Inject
  public RestaurantsPresenter(GrabLetsClient grabLetsClient, RestaurantsRepository restaurantsRepository) {
    this.grabLetsClient = grabLetsClient;
    this.restaurantsRepository = restaurantsRepository;
  }

  @Override
  public void getRestaurants() {
    Observable.defer(grabLetsClient::getRestaurants)
        .map(ApiToDbConverter::fromRestaurants)
        .flatMap(restaurants -> Observable.zip(Observable.just(restaurants), persistRestaurants(restaurants).toObservable(), (Func2<List<DbRestaurant>, Object, List<DbRestaurant>>) (restaurants1, o) -> restaurants1))
        .map(DbToViewModelConverter::fromRestaurants)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onRestaurantsPulled,
            this::onRestaurantsPullingFailed);
  }

  private void onRestaurantsPulled(RestaurantsViewModel restaurantsViewModel) {
    if (isViewAttached()) {
      getView().renderRestaurants(restaurantsViewModel);
    }
  }

  private void onRestaurantsPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }

  private Completable persistRestaurants(List<DbRestaurant> restaurants) {
    return restaurantsRepository.deleteRestaurants().endWith(
        restaurantsRepository.saveRestaurants(restaurants));
  }
}
