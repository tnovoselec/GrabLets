package com.grablets.mvp;

import com.grablets.viewmodel.DailyMenuOverlayViewModel;

public class DailyMenuOverlayMvp {

  public interface Presenter{
    void getDailyMenu();

    void onMenuItemSelected(String menuItemId);
  }

  public interface View extends MvpView{
    void renderDailyMenu(DailyMenuOverlayViewModel dailyMenuViewModel);

    void showCheckoutScreen();
  }
}
