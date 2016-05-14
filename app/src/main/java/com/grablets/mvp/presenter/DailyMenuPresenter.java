package com.grablets.mvp.presenter;

import com.grablets.mock.MockDailyMenu;
import com.grablets.mvp.DailyMenuMvp;

public class DailyMenuPresenter extends SubscribingPresenter<DailyMenuMvp.View> implements DailyMenuMvp.Presenter {
  @Override
  public void getDailyMenu() {
    if (isViewAttached()) {
      getView().renderDailyMenu(MockDailyMenu.getDailyMenu());
    }
  }
}
