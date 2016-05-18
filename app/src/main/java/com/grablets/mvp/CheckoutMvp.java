package com.grablets.mvp;

import com.grablets.viewmodel.CheckoutViewModel;

public class CheckoutMvp {

  public interface Presenter {
    void getCheckoutData();
  }

  public interface View extends MvpView {
    void renderCheckoutData(CheckoutViewModel checkoutViewModel);
  }
}
