package com.grablets.repository;

import com.grablets.db.BasketItemDao;
import com.grablets.db.model.DbBasketItem;

import java.util.List;

import rx.Completable;
import rx.Observable;

public class BasketDbRepository {

  private final BasketItemDao basketItemDao;

  public BasketDbRepository(BasketItemDao basketItemDao) {
    this.basketItemDao = basketItemDao;
  }

  public Observable<List<DbBasketItem>> getBasketItems(){
    return basketItemDao.getBasket();
  }

  public Completable insertBasketItem(String menuItemId, int amount){
    return basketItemDao.insertBasketItem(menuItemId, amount);
  }

  public Completable deleteBasketItem(String menuItemId){
    return basketItemDao.deleteBasketItem(menuItemId);
  }

  public Completable clearBasketItems(){
    return basketItemDao.clearBasketItems();
  }
}
