package com.grablets.mvp.presenter;

import com.grablets.mock.MockRestaurants;
import com.grablets.mvp.RestaurantsMvp;

import javax.inject.Inject;

public class RestaurantsPresenter extends SubscribingPresenter<RestaurantsMvp.View> implements RestaurantsMvp.Presenter {

  @Inject
  public RestaurantsPresenter() {
  }

  @Override
  public void getRestaurants() {
    if (isViewAttached()){
      getView().renderRestaurants(MockRestaurants.getRestaurants());
    }
  }
}
