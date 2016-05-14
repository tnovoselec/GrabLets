package com.grablets.mvp.presenter;

import android.util.Log;

import com.grablets.mvp.MvpView;

import java.lang.ref.WeakReference;

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

  public static final String TAG = MvpBasePresenter.class.getSimpleName();

  private WeakReference<V> view;

  public V getView() {
    return view == null ? null : view.get();
  }

  public boolean isViewAttached() {
    return view != null && view.get() != null;
  }

  @Override
  public void attachView(V view) {
    this.view = new WeakReference<>(view);
  }

  @Override
  public void detachView() {
    if (view != null) {
      view.clear();
      view = null;
    }
  }

  protected void responseError(final Throwable error) {
    Log.e(TAG, "An error occurred in the presenter. ", error);
  }

  public void responseComplete() {
  }
}
