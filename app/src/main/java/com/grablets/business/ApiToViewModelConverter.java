package com.grablets.business;

import com.grablets.api.model.ApiRestaurant;
import com.grablets.viewmodel.RestaurantsViewModel;
import com.grablets.viewmodel.RestaurantsViewModel.RestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

public class ApiToViewModelConverter {

  public static RestaurantsViewModel fromRestaurants(List<ApiRestaurant> restaurants){
    List<RestaurantViewModel> restaurantViewModels = new ArrayList<>();

    for (ApiRestaurant apiRestaurant : restaurants){
      RestaurantViewModel restaurantViewModel = new RestaurantViewModel(
          apiRestaurant.title,
          apiRestaurant.description,
          apiRestaurant.imageUrl
      );
      restaurantViewModels.add(restaurantViewModel);
    }

    return new RestaurantsViewModel(restaurantViewModels);
  }
}
