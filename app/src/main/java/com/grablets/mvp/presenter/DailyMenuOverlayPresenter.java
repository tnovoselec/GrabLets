package com.grablets.mvp.presenter;

import com.grablets.business.BasketManager;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.ClearBasketUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.DailyMenuOverlayMvp;
import com.grablets.viewmodel.DailyMenuOverlayViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyMenuOverlayPresenter extends SubscribingPresenter<DailyMenuOverlayMvp.View> implements DailyMenuOverlayMvp.Presenter {

  private final GetRestaurantsUseCase getRestaurantsUseCase;
  private final BasketManager basketManager;
  private final ClearBasketUseCase clearBasketUseCase;

  @Inject
  public DailyMenuOverlayPresenter(GetRestaurantsUseCase getRestaurantsUseCase, ClearBasketUseCase clearBasketUseCase, BasketManager basketManager) {
    this.getRestaurantsUseCase = getRestaurantsUseCase;
    this.basketManager = basketManager;
    this.clearBasketUseCase = clearBasketUseCase;
  }

  @Override
  public void getDailyMenu() {
    addSubscription(Observable.defer(getRestaurantsUseCase::execute)
        .map(DbToViewModelConverter::fromRestaurantsToDailyMenuOverlay)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onDailyMenuPulled,
            this::onDailyMenuPullingFailed));
  }

  @Override
  public void onMenuItemSelected(String menuItemId) {
    clearBasketUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onBasketClearFailed,
            () -> onBasketCleared(menuItemId));

  }

  private void onDailyMenuPulled(DailyMenuOverlayViewModel dailyMenuOverlayViewModel) {
    if (isViewAttached()) {
      getView().renderDailyMenu(dailyMenuOverlayViewModel);
    }
  }

  private void onDailyMenuPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }

  private void onBasketCleared(String menuItemId) {
    basketManager.basketEntryChanged(menuItemId, 1);
    if (isViewAttached()) {
      getView().showCheckoutScreen();
    }
  }

  private void onBasketClearFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
