package com.grablets.mock;

import com.grablets.viewmodel.MyOrdersViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.grablets.viewmodel.MyOrdersViewModel.MenuItemViewModel;
import static com.grablets.viewmodel.MyOrdersViewModel.OrderItemViewModel;
import static com.grablets.viewmodel.MyOrdersViewModel.RestaurantViewModel;

public class MockMyOrders {

  public static MyOrdersViewModel mock() {
    List<OrderItemViewModel> orderItemViewModels = new ArrayList<>();
    List<MyOrdersViewModel.MenuItemViewModel> menuItemViewModels = new ArrayList<>();

    MenuItemViewModel menuItemViewModel = new MenuItemViewModel("0", "Ćevapi s lukom", 2, 30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel("1", "Čobanac", 3, 30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel("2", "Odojak", 1, 50.00);
    menuItemViewModels.add(menuItemViewModel);

    List<RestaurantViewModel> restaurantViewModels = new ArrayList<>();
    RestaurantViewModel restaurantViewModel = new RestaurantViewModel("0", "Grana", menuItemViewModels);
    restaurantViewModels.add(restaurantViewModel);


    OrderItemViewModel orderItemViewModel = new OrderItemViewModel(1, System.currentTimeMillis(), OrderItemViewModel.Status.COMPLETED, restaurantViewModels);
    orderItemViewModels.add(orderItemViewModel);

    menuItemViewModels = new ArrayList<>();

    menuItemViewModel = new MenuItemViewModel("2", "Ćevapi s lukom", 2, 30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel("3", "Čobanac", 3, 30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel("4", "Odojak", 1, 50.00);
    menuItemViewModels.add(menuItemViewModel);
    restaurantViewModel = new RestaurantViewModel("1", "Kod Dede", menuItemViewModels);

    restaurantViewModels = new ArrayList<>();
    restaurantViewModels.add(restaurantViewModel);
    restaurantViewModel = new RestaurantViewModel("2", "Karaka", menuItemViewModels);
    restaurantViewModels.add(restaurantViewModel);

    orderItemViewModel = new OrderItemViewModel(2, System.currentTimeMillis(), OrderItemViewModel.Status.COMPLETED, restaurantViewModels);
    orderItemViewModels.add(orderItemViewModel);

    return new MyOrdersViewModel(orderItemViewModels);
  }
}
