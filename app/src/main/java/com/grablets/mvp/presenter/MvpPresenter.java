package com.grablets.mvp.presenter;

import com.grablets.mvp.MvpView;

public interface MvpPresenter<V extends MvpView> {

  void attachView(V view);

  void detachView();
}