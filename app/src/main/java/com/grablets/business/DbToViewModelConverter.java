package com.grablets.business;

import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.viewmodel.DailyMenuViewModel;
import com.grablets.viewmodel.DailyMenuViewModel.MenuItemViewModel;
import com.grablets.viewmodel.RestaurantsViewModel;
import com.grablets.viewmodel.RestaurantsViewModel.RestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

public class DbToViewModelConverter {

  public static RestaurantsViewModel fromRestaurants(List<DbRestaurant> restaurants){
    List<RestaurantViewModel> restaurantViewModels = new ArrayList<>();

    for (DbRestaurant dbRestaurant : restaurants){
      RestaurantViewModel restaurantViewModel = new RestaurantViewModel(
          dbRestaurant.getTitle(),
          dbRestaurant.getDescription(),
          dbRestaurant.getImageUrl()
      );
      restaurantViewModels.add(restaurantViewModel);
    }

    return new RestaurantsViewModel(restaurantViewModels);
  }

  public static List<MenuItemViewModel> fromMenuItems(List<DbRestaurantMenuItem> restaurantMenuItems){
    List<MenuItemViewModel> menuItemViewModels = new ArrayList<>();
    for (DbRestaurantMenuItem dbRestaurantMenuItem: restaurantMenuItems){
      MenuItemViewModel menuItemViewModel = new MenuItemViewModel(
          dbRestaurantMenuItem.getTitle(),
          dbRestaurantMenuItem.getDescription(),
          dbRestaurantMenuItem.getImageUrl()
      );
      menuItemViewModels.add(menuItemViewModel);
    }
    return menuItemViewModels;
  }

  public static DailyMenuViewModel fromRestaurantsToDailyMenu(List<DbRestaurant> restaurants){
    List<MenuItemViewModel> menuItemViewModels = new ArrayList<>();
    for (DbRestaurant dbRestaurant : restaurants){
      menuItemViewModels.addAll(fromMenuItems(dbRestaurant.getDbRestaurantMenuItems()));
    }
    return new DailyMenuViewModel(menuItemViewModels);
  }
}
