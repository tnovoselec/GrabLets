package com.grablets.mvp.presenter;

import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.DailyMenuMvp;
import com.grablets.viewmodel.DailyMenuViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyMenuPresenter extends SubscribingPresenter<DailyMenuMvp.View> implements DailyMenuMvp.Presenter {

  private final GetRestaurantsUseCase getRestaurantsUseCase;

  @Inject
  public DailyMenuPresenter(GetRestaurantsUseCase getRestaurantsUseCase) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
  }

  @Override
  public void getDailyMenu() {
    Observable.defer(getRestaurantsUseCase::execute)
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
}
