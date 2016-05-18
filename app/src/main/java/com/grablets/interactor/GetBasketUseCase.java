package com.grablets.interactor;

import com.grablets.db.model.DbBasketItem;
import com.grablets.repository.BasketDbRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetBasketUseCase implements UseCase<List<DbBasketItem>> {

  private final BasketDbRepository basketDbRepository;

  @Inject
  public GetBasketUseCase(BasketDbRepository basketDbRepository) {
    this.basketDbRepository = basketDbRepository;
  }

  @Override
  public Observable<List<DbBasketItem>> execute() {
    return basketDbRepository.getBasketItems();
  }
}
