package com.grablets.viewmodel;

public class MenuItemViewModel {
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
