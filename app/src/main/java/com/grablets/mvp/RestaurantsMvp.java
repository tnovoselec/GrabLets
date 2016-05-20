package com.grablets.mvp;

import com.grablets.viewmodel.RestaurantsViewModel;

public class RestaurantsMvp {

  public interface Presenter {
    void getRestaurants();

    void onRestaurantClicked(String restaurantId, String restaurantName);
  }

  public interface View extends MvpView {
    void renderRestaurants(RestaurantsViewModel restaurantsViewModel);
  }
}
