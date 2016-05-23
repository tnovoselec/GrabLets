package com.grablets.di.module;

import com.grablets.Router;
import com.grablets.business.BasketManager;
import com.grablets.di.scope.ActivityScope;
import com.grablets.interactor.ChangeRestaurantFavouriteStatusUseCase;
import com.grablets.interactor.ClearBasketUseCase;
import com.grablets.interactor.CreateOrderUseCase;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetFavouriteRestaurantsUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsByIdUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.presenter.CheckoutPresenter;
import com.grablets.mvp.presenter.DailyMenuOverlayPresenter;
import com.grablets.mvp.presenter.DailyMenuPresenter;
import com.grablets.mvp.presenter.LoginPresenter;
import com.grablets.mvp.presenter.MyRestaurantsPresenter;
import com.grablets.mvp.presenter.RegistrationPresenter;
import com.grablets.mvp.presenter.RestaurantMenuPresenter;
import com.grablets.mvp.presenter.RestaurantsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @ActivityScope
  DailyMenuPresenter dailyMenuPresenter(GetRestaurantsUseCase getRestaurantsUseCase, BasketManager basketManager) {
    return new DailyMenuPresenter(getRestaurantsUseCase, basketManager);
  }

  @Provides
  @ActivityScope
  RestaurantsPresenter restaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase,
                                            GetFavouriteRestaurantsUseCase getFavouriteRestaurantsUseCase,
                                            ChangeRestaurantFavouriteStatusUseCase changeRestaurantFavouriteStatusUseCase,
                                            Router router) {
    return new RestaurantsPresenter(getRestaurantsUseCase, getFavouriteRestaurantsUseCase, changeRestaurantFavouriteStatusUseCase, router);
  }

  @Provides
  @ActivityScope
  MyRestaurantsPresenter myRestaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase,
                                                GetFavouriteRestaurantsUseCase getFavouriteRestaurantsUseCase,
                                                ChangeRestaurantFavouriteStatusUseCase changeRestaurantFavouriteStatusUseCase,
                                                Router router) {
    return new MyRestaurantsPresenter(getRestaurantsUseCase, getFavouriteRestaurantsUseCase, changeRestaurantFavouriteStatusUseCase, router);
  }

  @Provides
  @ActivityScope
  DailyMenuOverlayPresenter dailyMenuOverlayPresenter(GetRestaurantsUseCase getRestaurantsUseCase,
                                                      ClearBasketUseCase clearBasketUseCase,
                                                      BasketManager basketManager) {
    return new DailyMenuOverlayPresenter(getRestaurantsUseCase, clearBasketUseCase, basketManager);
  }

  @Provides
  @ActivityScope
  LoginPresenter loginPresenter(Router router) {
    return new LoginPresenter(router);
  }

  @Provides
  @ActivityScope
  RegistrationPresenter registrationPresenter(Router router) {
    return new RegistrationPresenter(router);
  }

  @Provides
  @ActivityScope
  CheckoutPresenter checkoutPresenter(GetBasketUseCase getBasketUseCase,
                                      GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase,
                                      ClearBasketUseCase clearBasketUseCase,
                                      CreateOrderUseCase createOrderUseCase,
                                      Router router,
                                      BasketManager basketManager) {
    return new CheckoutPresenter(getBasketUseCase, getRestaurantMenuItemsUseCase, clearBasketUseCase, createOrderUseCase, router, basketManager);
  }

  @Provides
  @ActivityScope
  RestaurantMenuPresenter restaurantMenuPresenter(GetRestaurantMenuItemsByIdUseCase getRestaurantMenuItemsByIdUseCase,
                                                  BasketManager basketManager) {
    return new RestaurantMenuPresenter(getRestaurantMenuItemsByIdUseCase, basketManager);
  }
}
