package com.grablets.viewmodel;

import java.util.List;

public class DailyMenuViewModel {

  public final List<MenuItemViewModel> menuItemViewModels;

  public DailyMenuViewModel(List<MenuItemViewModel> menuItemViewModels) {
    this.menuItemViewModels = menuItemViewModels;
  }

  public static class MenuItemViewModel{
    public final String id;
    public final String title;
    public final String imageUrl;
    public final String description;
    public final double price;

    public MenuItemViewModel(String id, String title, String description, String imageUrl, double price) {
      this.id = id;
      this.description = description;
      this.imageUrl = imageUrl;
      this.title = title;
      this.price = price;
    }
  }
}
