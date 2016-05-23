package com.grablets.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.grablets.Router;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.ChangeRestaurantFavouriteStatusUseCase;
import com.grablets.interactor.GetFavouriteRestaurantsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.MyRestaurantsMvp;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyRestaurantsPresenter extends SubscribingPresenter<MyRestaurantsMvp.View> implements MyRestaurantsMvp.Presenter {

  private final GetRestaurantsUseCase getRestaurantsUseCase;
  private final GetFavouriteRestaurantsUseCase getFavouriteRestaurantsUseCase;
  private final ChangeRestaurantFavouriteStatusUseCase changeRestaurantFavouriteStatusUseCase;
  private final Router router;

  @Inject
  public MyRestaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase, GetFavouriteRestaurantsUseCase getFavouriteRestaurantsUseCase, ChangeRestaurantFavouriteStatusUseCase changeRestaurantFavouriteStatusUseCase, Router router) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
    this.getFavouriteRestaurantsUseCase = getFavouriteRestaurantsUseCase;
    this.changeRestaurantFavouriteStatusUseCase = changeRestaurantFavouriteStatusUseCase;
    this.router = router;
  }

  @Override
  public void getRestaurants() {
    addSubscription(Observable.defer(getRestaurantsUseCase::execute)
        .zipWith(getFavouriteRestaurantsUseCase.execute(), (dbRestaurants, strings) -> {
          return DbToViewModelConverter.fromRestaurantsAndFavourites(Stream.of(dbRestaurants).filter((object) -> strings.contains(object.getId())).collect(Collectors.toList()), strings);
        })
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
    changeRestaurantFavouriteStatusUseCase.execute(restaurantId, isFavourite)
        .subscribeOn(Schedulers.io());
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
