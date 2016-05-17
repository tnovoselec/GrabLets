package com.grablets.mvp.presenter;

import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.DailyMenuOverlayMvp;
import com.grablets.viewmodel.DailyMenuOverlayViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyMenuOverlayPresenter extends SubscribingPresenter<DailyMenuOverlayMvp.View> implements DailyMenuOverlayMvp.Presenter {

  private final GetRestaurantsUseCase getRestaurantsUseCase;

  @Inject
  public DailyMenuOverlayPresenter(GetRestaurantsUseCase getRestaurantsUseCase) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
  }

  @Override
  public void getDailyMenu() {
    Observable.defer(getRestaurantsUseCase::execute)
        .map(DbToViewModelConverter::fromRestaurantsToDailyMenuOverlay)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onDailyMenuPulled,
            this::onDailyMenuPullingFailed);
  }

  private void onDailyMenuPulled(DailyMenuOverlayViewModel dailyMenuOverlayViewModel) {
    if (getView() != null) {
      getView().renderDailyMenu(dailyMenuOverlayViewModel);
    }
  }

  private void onDailyMenuPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
