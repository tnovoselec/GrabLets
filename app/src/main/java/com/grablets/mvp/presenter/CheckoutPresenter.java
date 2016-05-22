package com.grablets.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.grablets.R;
import com.grablets.Router;
import com.grablets.business.BasketManager;
import com.grablets.business.DbToViewModelConverter;
import com.grablets.db.model.DbBasketItem;
import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.interactor.ClearBasketUseCase;
import com.grablets.interactor.CreateOrderUseCase;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.mvp.CheckoutMvp;
import com.grablets.viewmodel.CheckoutViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
  private final ClearBasketUseCase clearBasketUseCase;
  private final CreateOrderUseCase createOrderUseCase;
  private final Router router;
  private final BasketManager basketManager;

  @Inject
  public CheckoutPresenter(GetBasketUseCase getBasketUseCase, GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase, ClearBasketUseCase clearBasketUseCase, CreateOrderUseCase createOrderUseCase, Router router, BasketManager basketManager) {
    this.getBasketUseCase = getBasketUseCase;
    this.getRestaurantMenuItemsUseCase = getRestaurantMenuItemsUseCase;
    this.clearBasketUseCase = clearBasketUseCase;
    this.createOrderUseCase = createOrderUseCase;
    this.router = router;
    this.basketManager = basketManager;
  }

  @Override
  public void getCheckoutData() {
    addSubscription(Observable.defer(getBasketUseCase::execute)
        .flatMap(basketItems -> {
          final Set<String> ids = collectIds(basketItems);
          return getRestaurantMenuItemsUseCase.execute(ids);
        }, new Func2<List<DbBasketItem>, List<DbRestaurantMenuItem>, CheckoutViewModel>() {
          @Override
          public CheckoutViewModel call(List<DbBasketItem> basketItems, List<DbRestaurantMenuItem> dbRestaurantMenuItems) {
            return DbToViewModelConverter.fromBasketAndUserData(basketItems, dbRestaurantMenuItems);
          }
        })
        .map(checkoutViewModel -> {
          Comparator<CheckoutViewModel.CheckoutMenuItemViewModel> comparator = (lhs, rhs) -> lhs.title.compareTo(rhs.title);
          Collections.sort(checkoutViewModel.checkoutMenuItemViewModels, comparator);
          return checkoutViewModel;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onCheckoutDataPulled,
            this::onCheckoutDataPullingFailed));
  }

  @Override
  public void onMenuItemAmountChanged(String menuItemId, int newAmount) {
    basketManager.basketEntryChanged(menuItemId, newAmount);
    getCheckoutData();
  }

  @Override
  public void onConfirmOrderClicked(String address, Date deliveryTime) {
    addSubscription(createOrderUseCase.execute()
        .flatMap(result -> clearBasketUseCase.execute().andThen(Observable.just(result)))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            ignored -> {
              onOrderSuccessfullyCreated();
            },
            this::onOrderCreationFailed
        ));
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

  private void onOrderSuccessfullyCreated() {
    if (isViewAttached()) {
      getView().showOrderSuccessfulMessage(R.string.order_successful);
      router.finishCurrentActivity();
    }
  }

  private void onOrderCreationFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
