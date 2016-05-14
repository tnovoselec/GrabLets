package com.grablets.viewmodel;

import java.util.List;

public class RestaurantsViewModel {

  public final List<RestaurantViewModel> restaurantViewModels;

  public RestaurantsViewModel(List<RestaurantViewModel> restaurantViewModels) {
    this.restaurantViewModels = restaurantViewModels;
  }

  public static class RestaurantViewModel {
    public final String title;
    public final String description;
    public final String imageUrl;

    public RestaurantViewModel(String title, String description, String imageUrl) {
      this.description = description;
      this.title = title;
      this.imageUrl = imageUrl;
    }
  }
}
