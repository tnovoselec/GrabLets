package com.grablets.mvp;

import com.grablets.viewmodel.MyOrdersViewModel;

public class MyOrdersMvp {
  public interface Presenter {
    void getOrders();
  }

  public interface View extends MvpView {
    void renderOrders(MyOrdersViewModel myOrdersViewModel);
  }
}
