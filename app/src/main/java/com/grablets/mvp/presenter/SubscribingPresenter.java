package com.grablets.mvp.presenter;

import com.grablets.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class SubscribingPresenter<V extends MvpView> extends MvpBasePresenter<V> implements ScopedPresenter {

  private CompositeSubscription subscriptions;

  @Override
  public void activate() {
  }

  @Override
  public void deactivate() {
    unSubscribe();
  }

  protected void addSubscription(final Subscription subscription) {
    if (subscriptions == null || subscription.isUnsubscribed()) {
      subscriptions = new CompositeSubscription();
    }

    subscriptions.add(subscription);
  }

  private void unSubscribe() {
    if (subscriptions != null && !subscriptions.isUnsubscribed()) {
      subscriptions.unsubscribe();
    }

    subscriptions = null;
  }
}