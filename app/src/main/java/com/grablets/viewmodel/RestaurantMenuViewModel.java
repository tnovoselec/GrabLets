package com.grablets.viewmodel;

import java.util.List;
import java.util.Map;

public class RestaurantMenuViewModel {

  public final List<MenuItemViewModel> menuItemViewModels;

  public final Map<String, Integer> basketEntries;

  public RestaurantMenuViewModel(List<MenuItemViewModel> menuItemViewModels, Map<String, Integer> basketEntries) {
    this.menuItemViewModels = menuItemViewModels;
    this.basketEntries = basketEntries;
  }

}
