package com.grablets.mvp.presenter;

import com.grablets.api.GrabLetsClient;
import com.grablets.business.ApiToDbConverter;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.db.model.DbRestaurant;
import com.grablets.mvp.DailyMenuMvp;
import com.grablets.repository.RestaurantsRepository;
import com.grablets.viewmodel.DailyMenuViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyMenuPresenter extends SubscribingPresenter<DailyMenuMvp.View> implements DailyMenuMvp.Presenter {

  private final GrabLetsClient grabLetsClient;
  private final RestaurantsRepository restaurantsRepository;

  @Inject
  public DailyMenuPresenter(GrabLetsClient grabLetsClient, RestaurantsRepository restaurantsRepository) {
    this.grabLetsClient = grabLetsClient;
    this.restaurantsRepository = restaurantsRepository;
  }

  @Override
  public void getDailyMenu() {
    Observable.defer(grabLetsClient::getRestaurants)
        .map(ApiToDbConverter::fromRestaurants)
        .doOnNext(this::persistRestaurants)
        .map(DbToViewModelConverter::fromRestaurantsToDailyMenu)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onDailyMenuPulled,
            this::onDailyMenuPullingFailed);
  }

  private void onDailyMenuPulled(DailyMenuViewModel dailyMenuViewModel) {
    if (isViewAttached()) {
      getView().renderDailyMenu(dailyMenuViewModel);
    }
  }

  private void onDailyMenuPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }

  private void persistRestaurants(List<DbRestaurant> restaurants){
    restaurantsRepository.saveRestaurants(restaurants);
  }
}
