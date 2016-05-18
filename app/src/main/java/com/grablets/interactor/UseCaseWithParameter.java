package com.grablets.interactor;

import rx.Observable;

public interface UseCaseWithParameter<T, U> {

  Observable<T> execute(U parameter);
}
