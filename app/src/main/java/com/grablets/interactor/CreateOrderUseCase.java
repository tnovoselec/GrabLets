package com.grablets.interactor;

import rx.Observable;

public class CreateOrderUseCase implements UseCase<Boolean> {
  @Override
  public Observable<Boolean> execute() {
    return Observable.just(Boolean.TRUE);
  }
}
