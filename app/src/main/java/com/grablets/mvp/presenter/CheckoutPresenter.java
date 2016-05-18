package com.grablets.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.db.model.DbBasketItem;
import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.mvp.CheckoutMvp;
import com.grablets.viewmodel.CheckoutViewModel;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class CheckoutPresenter extends SubscribingPresenter<CheckoutMvp.View> implements CheckoutMvp.Presenter {

  private final GetBasketUseCase getBasketUseCase;
  private final GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase;

  @Inject
  public CheckoutPresenter(GetBasketUseCase getBasketUseCase, GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase) {
    this.getBasketUseCase = getBasketUseCase;
    this.getRestaurantMenuItemsUseCase = getRestaurantMenuItemsUseCase;
  }

  @Override
  public void getCheckoutData() {
    Observable.defer(getBasketUseCase::execute)
        .flatMap(basketItems -> {
          final Set<String> ids = collectIds(basketItems);
          return getRestaurantMenuItemsUseCase.execute(ids);
        }, new Func2<List<DbBasketItem>, List<DbRestaurantMenuItem>, CheckoutViewModel>() {
          @Override
          public CheckoutViewModel call(List<DbBasketItem> basketItems, List<DbRestaurantMenuItem> dbRestaurantMenuItems) {
            return DbToViewModelConverter.fromBasketAndUserData(basketItems, dbRestaurantMenuItems);
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onCheckoutDataPulled,
            this::onCheckoutDataPullingFailed);
  }

  private Set<String> collectIds(List<DbBasketItem> basketItems) {
    return Stream.of(basketItems)
        .map(DbBasketItem::getMenuItemId)
        .collect(Collectors.toSet());
  }

  private void onCheckoutDataPulled(CheckoutViewModel checkoutViewModel) {
    if (isViewAttached()) {
      getView().renderCheckoutData(checkoutViewModel);
    }
  }

  private void onCheckoutDataPullingFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
