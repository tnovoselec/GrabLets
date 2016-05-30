package com.grablets.mvp.presenter;

import com.grablets.mock.MockMyOrders;
import com.grablets.mvp.MyOrdersMvp;

import javax.inject.Inject;

public class MyOrdersPresenter extends SubscribingPresenter<MyOrdersMvp.View> implements MyOrdersMvp.Presenter {


  @Inject
  public MyOrdersPresenter() {
  }

  @Override
  public void getOrders() {
    if (isViewAttached()) {
      getView().renderOrders(MockMyOrders.mock());
    }
  }
}
