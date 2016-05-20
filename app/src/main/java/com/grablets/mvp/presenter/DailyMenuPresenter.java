package com.grablets.mvp.presenter;

import com.grablets.business.BasketManager;
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
  private final BasketManager basketManager;

  @Inject
  public DailyMenuPresenter(GetRestaurantsUseCase getRestaurantsUseCase, BasketManager basketManager) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
    this.basketManager = basketManager;
  }

  @Override
  public void getDailyMenu() {
    addSubscription(Observable.defer(getRestaurantsUseCase::execute)
        .map(dbRestaurants -> DbToViewModelConverter.fromRestaurantsToDailyMenu(dbRestaurants, basketManager.getBasketEntries()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onDailyMenuPulled,
            this::onDailyMenuPullingFailed));
  }

  @Override
  public void onMenuItemAmountChanged(String menuItemId, int newAmount) {
    basketManager.basketEntryChanged(menuItemId, newAmount);
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
