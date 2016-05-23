package com.grablets.mvp.presenter;

import com.grablets.Router;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.MyRestaurantsMvp;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyRestaurantsPresenter extends SubscribingPresenter<MyRestaurantsMvp.View> implements MyRestaurantsMvp.Presenter {

  private final GetRestaurantsUseCase getRestaurantsUseCase;
  private final Router router;

  @Inject
  public MyRestaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase, Router router) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
    this.router = router;
  }

  @Override
  public void getRestaurants() {
    addSubscription(Observable.defer(getRestaurantsUseCase::execute)
        .map(DbToViewModelConverter::fromRestaurants)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onRestaurantsPulled,
            this::onRestaurantsPullingFailed));
  }

  @Override
  public void onRestaurantClicked(String restaurantId, String restaurantName) {
    router.startRestaurantMenuActivity(restaurantId, restaurantName);
  }

  @Override
  public void onRestaurantFavourited(String restaurantId, boolean isFavourite) {

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
