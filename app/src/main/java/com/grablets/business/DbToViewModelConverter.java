package com.grablets.business;

import com.grablets.db.model.DbRestaurant;
import com.grablets.viewmodel.RestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;

public class DbToViewModelConverter {

  public static RestaurantsViewModel fromRestaurants(List<DbRestaurant> restaurants){
    List<RestaurantsViewModel.RestaurantViewModel> restaurantViewModels = new ArrayList<>();

    for (DbRestaurant dbRestaurant : restaurants){
      RestaurantsViewModel.RestaurantViewModel restaurantViewModel = new RestaurantsViewModel.RestaurantViewModel(
          dbRestaurant.getTitle(),
          dbRestaurant.getDescription(),
          dbRestaurant.getImageUrl()
      );
      restaurantViewModels.add(restaurantViewModel);
    }

    return new RestaurantsViewModel(restaurantViewModels);
  }
}
