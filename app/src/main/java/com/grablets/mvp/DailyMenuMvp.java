package com.grablets.mvp;

import com.grablets.viewmodel.DailyMenuViewModel;

public class DailyMenuMvp {

  public interface Presenter {
    void getDailyMenu();

    void onMenuItemAmountChanged(String menuItemId, int newAmount);
  }

  public interface View extends MvpView{
    void renderDailyMenu(DailyMenuViewModel dailyMenuViewModel);
  }
}
