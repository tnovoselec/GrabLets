package com.grablets.mvp;

import com.grablets.viewmodel.RestaurantMenuViewModel;

public class RestaurantMenuMvp {

  public interface Presenter {
    void getRestaurantMenu(String restaurantId);

    void onMenuItemAmountChanged(String menuItemId, int newAmount);
  }

  public interface View extends MvpView{
    void renderDailyMenu(RestaurantMenuViewModel restaurantMenuViewModel);
  }
}
