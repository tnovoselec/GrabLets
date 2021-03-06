package com.grablets.di.module;

import com.grablets.Router;
import com.grablets.business.BasketManager;
import com.grablets.di.scope.ActivityScope;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.mvp.presenter.CheckoutPresenter;
import com.grablets.mvp.presenter.DailyMenuOverlayPresenter;
import com.grablets.mvp.presenter.DailyMenuPresenter;
import com.grablets.mvp.presenter.LoginPresenter;
import com.grablets.mvp.presenter.RegistrationPresenter;
import com.grablets.mvp.presenter.RestaurantsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @ActivityScope
  DailyMenuPresenter dailyMenuPresenter(GetRestaurantsUseCase getRestaurantsUseCase, BasketManager basketManager){
    return new DailyMenuPresenter(getRestaurantsUseCase, basketManager);
  }

  @Provides
  @ActivityScope
  RestaurantsPresenter restaurantsPresenter(GetRestaurantsUseCase getRestaurantsUseCase){
    return new RestaurantsPresenter(getRestaurantsUseCase);
  }

  @Provides
  @ActivityScope
  DailyMenuOverlayPresenter dailyMenuOverlayPresenter(GetRestaurantsUseCase getRestaurantsUseCase){
    return new DailyMenuOverlayPresenter(getRestaurantsUseCase);
  }

  @Provides
  @ActivityScope
  LoginPresenter loginPresenter(Router router){
    return new LoginPresenter(router);
  }

  @Provides
  @ActivityScope
  RegistrationPresenter registrationPresenter(Router router){
    return new RegistrationPresenter(router);
  }

  @Provides
  @ActivityScope
  CheckoutPresenter checkoutPresenter(GetBasketUseCase getBasketUseCase, GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase){
    return new CheckoutPresenter(getBasketUseCase, getRestaurantMenuItemsUseCase);
  }
}
