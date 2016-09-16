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
    this.imageUrl = "http://46.101.111.210/" + imageUrl;
    this.title = title;
    this.price = price;
  }
}
