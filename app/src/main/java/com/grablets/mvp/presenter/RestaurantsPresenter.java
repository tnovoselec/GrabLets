package com.grablets.mvp.presenter;

import com.grablets.api.GrabLetsClient;
import com.grablets.business.ApiToViewModelConverter;
import com.grablets.mvp.RestaurantsMvp;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantsPresenter extends SubscribingPresenter<RestaurantsMvp.View> implements RestaurantsMvp.Presenter {

  private final GrabLetsClient grabLetsClient;

  @Inject
  public RestaurantsPresenter(GrabLetsClient grabLetsClient) {
    this.grabLetsClient = grabLetsClient;
  }

  @Override
  public void getRestaurants() {
    Observable.defer(grabLetsClient::getRestaurants)
        .map(ApiToViewModelConverter::fromRestaurants)
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
}
