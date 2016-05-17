package com.grablets.mvp.presenter;

import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.RestaurantsMvp;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantsPresenter extends SubscribingPresenter<RestaurantsMvp.View> implements RestaurantsMvp.Presenter {

 private final GetRestaurantsUseCase getRestaurantsUseCase;

  @Inject
  public RestaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
  }

  @Override
  public void getRestaurants() {
    Observable.defer(getRestaurantsUseCase::execute)
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


}
