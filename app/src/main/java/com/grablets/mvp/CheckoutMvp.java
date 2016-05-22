package com.grablets.mvp;

import android.support.annotation.StringRes;

import com.grablets.viewmodel.CheckoutViewModel;

import java.util.Date;

public class CheckoutMvp {

  public interface Presenter {
    void getCheckoutData();

    void onMenuItemAmountChanged(String menuItemId, int newAmount);

    void onConfirmOrderClicked(String address, Date deliveryTime);
  }

  public interface View extends MvpView {
    void renderCheckoutData(CheckoutViewModel checkoutViewModel);

    void showOrderSuccessfulMessage(@StringRes int messageId);
  }
}
