package com.grablets.mvp;

import com.grablets.viewmodel.RestaurantsViewModel;

public class MyRestaurantsMvp {

  public interface Presenter {
    void getRestaurants();

    void onRestaurantClicked(String restaurantId, String restaurantName);

    void onRestaurantFavourited(String restaurantId, boolean isFavourite);
  }

  public interface View extends MvpView {
    void renderRestaurants(RestaurantsViewModel restaurantsViewModel);
  }
}
