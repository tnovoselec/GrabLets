package com.grablets.mvp.presenter;

import com.grablets.business.BasketManager;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.interactor.GetRestaurantMenuItemsByIdUseCase;
import com.grablets.mvp.RestaurantMenuMvp;
import com.grablets.viewmodel.RestaurantMenuViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantMenuPresenter extends SubscribingPresenter<RestaurantMenuMvp.View> implements RestaurantMenuMvp.Presenter{

  private final GetRestaurantMenuItemsByIdUseCase getRestaurantMenuItemsByIdUseCase;
  private final BasketManager basketManager;

  @Inject
  public RestaurantMenuPresenter(GetRestaurantMenuItemsByIdUseCase getRestaurantMenuItemsByIdUseCase, BasketManager basketManager) {
    this.getRestaurantMenuItemsByIdUseCase = getRestaurantMenuItemsByIdUseCase;
    this.basketManager = basketManager;
  }


  @Override
  public void getRestaurantMenu(String restaurantId) {
    addSubscription(Observable.defer(() -> getRestaurantMenuItemsByIdUseCase.execute(restaurantId))
        .map(dbRestaurantMenuItems -> DbToViewModelConverter.fromMenuItems(dbRestaurantMenuItems, basketManager.getBasketEntries()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onRestaurantMenuPulled,
            this::onRestaurantMenuPullingFailed));
  }

  @Override
  public void onMenuItemAmountChanged(String menuItemId, int newAmount) {
    basketManager.basketEntryChanged(menuItemId, newAmount);
  }

  private void onRestaurantMenuPulled(RestaurantMenuViewModel restaurantMenuViewModel) {
    if (isViewAttached()) {
      getView().renderDailyMenu(restaurantMenuViewModel);
    }
  }

  private void onRestaurantMenuPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
