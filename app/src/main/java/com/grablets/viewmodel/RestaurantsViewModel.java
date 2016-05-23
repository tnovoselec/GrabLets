package com.grablets.viewmodel;

import java.util.List;

public class RestaurantsViewModel {

  public final List<RestaurantViewModel> restaurantViewModels;

  public RestaurantsViewModel(List<RestaurantViewModel> restaurantViewModels) {
    this.restaurantViewModels = restaurantViewModels;
  }

  public static class RestaurantViewModel {
    public final String id;
    public final String title;
    public final String description;
    public final String imageUrl;
    public final boolean isFavourite;

    public RestaurantViewModel(String id, String title, String description, String imageUrl, boolean isFavourite) {
      this.id = id;
      this.description = description;
      this.title = title;
      this.imageUrl = imageUrl;
      this.isFavourite = isFavourite;
    }
  }
}
