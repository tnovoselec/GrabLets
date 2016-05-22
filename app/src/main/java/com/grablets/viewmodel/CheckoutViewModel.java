package com.grablets.viewmodel;

import java.util.List;

public class CheckoutViewModel {

  public final UserDataViewModel userDataViewModel;
  public final List<CheckoutMenuItemViewModel> checkoutMenuItemViewModels;

  public CheckoutViewModel(List<CheckoutMenuItemViewModel> checkoutMenuItemViewModels, UserDataViewModel userDataViewModel) {
    this.checkoutMenuItemViewModels = checkoutMenuItemViewModels;
    this.userDataViewModel = userDataViewModel;
  }

  public static class UserDataViewModel {

    public final String deliveryAddress;
    public final String deliveryTime;

    public UserDataViewModel(String deliveryAddress, String deliveryTime) {
      this.deliveryAddress = deliveryAddress;
      this.deliveryTime = deliveryTime;
    }
  }

  public static class CheckoutMenuItemViewModel {

    public final String id;
    public final int amount;
    public final double price;
    public final String title;
    public final String imageUrl;

    public CheckoutMenuItemViewModel(int amount, String id, double price, String title, String imageUrl) {
      this.amount = amount;
      this.id = id;
      this.price = price;
      this.title = title;
      this.imageUrl = imageUrl;
    }
  }
}
