package com.grablets.viewmodel;

import java.util.List;
import java.util.Map;

public class DailyMenuViewModel {

  public final List<MenuItemViewModel> menuItemViewModels;

  public final Map<String, Integer> basketEntries;

  public DailyMenuViewModel(List<MenuItemViewModel> menuItemViewModels, Map<String, Integer> basketEntries) {
    this.menuItemViewModels = menuItemViewModels;
    this.basketEntries = basketEntries;
  }
}
