package com.grablets.mvp.presenter;

import com.grablets.mock.MockDailyMenu;
import com.grablets.mvp.DailyMenuMvp;

import javax.inject.Inject;

public class DailyMenuPresenter extends SubscribingPresenter<DailyMenuMvp.View> implements DailyMenuMvp.Presenter {

  @Inject
  public DailyMenuPresenter() {
  }

  @Override
  public void getDailyMenu() {
    if (isViewAttached()) {
      getView().renderDailyMenu(MockDailyMenu.getDailyMenu());
    }
  }
}
