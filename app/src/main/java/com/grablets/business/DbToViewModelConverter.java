package com.grablets.business;

import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.viewmodel.DailyMenuOverlayViewModel;
import com.grablets.viewmodel.DailyMenuViewModel;
import com.grablets.viewmodel.DailyMenuViewModel.MenuItemViewModel;
import com.grablets.viewmodel.RestaurantsViewModel;
import com.grablets.viewmodel.RestaurantsViewModel.RestaurantViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbToViewModelConverter {

  public static RestaurantsViewModel fromRestaurants(List<DbRestaurant> dbRestaurants) {
    List<RestaurantViewModel> restaurantViewModels = new ArrayList<>();

    for (DbRestaurant dbRestaurant : dbRestaurants) {
      RestaurantViewModel restaurantViewModel = new RestaurantViewModel(
          dbRestaurant.getTitle(),
          dbRestaurant.getDescription(),
          dbRestaurant.getImageUrl()
      );
      restaurantViewModels.add(restaurantViewModel);
    }

    return new RestaurantsViewModel(restaurantViewModels);
  }

  public static List<MenuItemViewModel> fromMenuItems(List<DbRestaurantMenuItem> restaurantMenuItems) {
    List<MenuItemViewModel> menuItemViewModels = new ArrayList<>();
    for (DbRestaurantMenuItem dbRestaurantMenuItem : restaurantMenuItems) {
      MenuItemViewModel menuItemViewModel = new MenuItemViewModel(
          dbRestaurantMenuItem.getTitle(),
          dbRestaurantMenuItem.getDescription(),
          dbRestaurantMenuItem.getImageUrl(),
          dbRestaurantMenuItem.getPrice());
      menuItemViewModels.add(menuItemViewModel);
    }
    return menuItemViewModels;
  }

  public static DailyMenuViewModel fromRestaurantsToDailyMenu(List<DbRestaurant> dbRestaurants) {
    List<MenuItemViewModel> menuItemViewModels = new ArrayList<>();
    for (DbRestaurant dbRestaurant : dbRestaurants) {
      menuItemViewModels.addAll(fromMenuItems(dbRestaurant.getDbRestaurantMenuItems()));
    }
    return new DailyMenuViewModel(menuItemViewModels);
  }

  public static DailyMenuOverlayViewModel fromRestaurantsToDailyMenuOverlay(List<DbRestaurant> dbRestaurants) {

    Map<DailyMenuOverlayViewModel.RestaurantOverlayViewModel, List<DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel>> data = new HashMap<>();

    for (DbRestaurant dbRestaurant : dbRestaurants) {
      DailyMenuOverlayViewModel.RestaurantOverlayViewModel restaurantOverlayViewModel = new DailyMenuOverlayViewModel.RestaurantOverlayViewModel(dbRestaurant.getTitle());
      List<DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel> dailyMenuOverlayViewModels = new ArrayList<>();

      for (DbRestaurantMenuItem dbRestaurantMenuItem : dbRestaurant.getDbRestaurantMenuItems()) {

        DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel dailyMenuOverlayViewModel = new DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel(
            dbRestaurantMenuItem.getTitle(),
            dbRestaurantMenuItem.getDescription(),
            dbRestaurantMenuItem.getImageUrl(),
            dbRestaurantMenuItem.getPrice()
        );
        dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);
      }
      data.put(restaurantOverlayViewModel, dailyMenuOverlayViewModels);
    }


    return new DailyMenuOverlayViewModel(data);
  }
}
