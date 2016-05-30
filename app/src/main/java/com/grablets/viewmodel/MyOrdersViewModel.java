package com.grablets.viewmodel;

import java.util.List;

public class MyOrdersViewModel {

  public final List<OrderItemViewModel> orderItemViewModels;

  public MyOrdersViewModel(List<OrderItemViewModel> orderItemViewModels) {
    this.orderItemViewModels = orderItemViewModels;
  }

  public static class OrderItemViewModel {

    public enum Status {
      PENDING, COMPLETED
    }

    public final long id;
    public final long orderTimestamp;
    public final Status status;
    public final List<RestaurantViewModel> restaurantViewModels;
    public final double total;

    public OrderItemViewModel(long id, long orderTimestamp, Status status, List<RestaurantViewModel> restaurantViewModels) {
      this.id = id;
      this.orderTimestamp = orderTimestamp;
      this.status = status;
      this.restaurantViewModels = restaurantViewModels;
      this.total = calculateTotal();
    }

    private double calculateTotal() {
      double total = 0;
      for (RestaurantViewModel restaurantViewModel : restaurantViewModels) {
        for (MenuItemViewModel menuItemViewModel : restaurantViewModel.menuItemViewModels) {
          total += menuItemViewModel.price * menuItemViewModel.quantity;
        }
      }
      return total;
    }

  }

  public static class RestaurantViewModel {
    public final String id;
    public final String title;
    public final List<MenuItemViewModel> menuItemViewModels;

    public RestaurantViewModel(String id, String title, List<MenuItemViewModel> menuItemViewModels) {
      this.id = id;
      this.title = title;
      this.menuItemViewModels = menuItemViewModels;
    }
  }

  public static class MenuItemViewModel {
    public final String id;
    public final String title;
    public final long quantity;
    public final double price;

    public MenuItemViewModel(String id, String title, long quantity, double price) {
      this.id = id;
      this.title = title;
      this.quantity = quantity;
      this.price = price;
    }
  }

}
