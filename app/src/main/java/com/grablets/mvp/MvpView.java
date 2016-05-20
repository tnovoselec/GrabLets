package com.grablets.mvp;

import android.support.annotation.StringRes;

public interface MvpView {

  void showErrorMessage(@StringRes int errorMessageId);
}
