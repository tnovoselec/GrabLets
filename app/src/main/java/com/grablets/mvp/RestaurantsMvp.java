package com.grablets.mvp;

import com.grablets.viewmodel.RestaurantsViewModel;

public class RestaurantsMvp {

  public interface Presenter {
    void getRestaurants();
  }

  public interface View extends MvpView {
    void renderRestaurants(RestaurantsViewModel restaurantsViewModel);
  }
}
