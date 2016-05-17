package com.grablets.interactor;


import rx.Observable;

public interface UseCase<T> {
  Observable<T> execute();
}
