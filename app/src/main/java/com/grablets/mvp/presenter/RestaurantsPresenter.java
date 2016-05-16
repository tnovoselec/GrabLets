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

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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
        .doOnNext(this::persistRestaurants)
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

  private void persistRestaurants(List<DbRestaurant> restaurants){
    restaurantsRepository.saveRestaurants(restaurants);
  }
}
