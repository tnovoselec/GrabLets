package com.grablets.interactor;

import com.grablets.repository.BasketDbRepository;

import rx.Completable;

public class ClearBasketUseCase{

  private final BasketDbRepository basketDbRepository;

  public ClearBasketUseCase(BasketDbRepository basketDbRepository) {
    this.basketDbRepository = basketDbRepository;
  }

  public Completable execute() {
    return basketDbRepository.clearBasketItems();
  }
}
