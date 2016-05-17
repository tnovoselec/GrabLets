package com.grablets.mvp;

import com.grablets.viewmodel.DailyMenuOverlayViewModel;

public class DailyMenuOverlayMvp {

  public interface Presenter{
    void getDailyMenu();
  }

  public interface View extends MvpView{
    void renderDailyMenu(DailyMenuOverlayViewModel dailyMenuViewModel);
  }
}
