package com.grablets.business;

import com.grablets.repository.BasketDbRepository;
import com.jakewharton.rxrelay.BehaviorRelay;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.schedulers.Schedulers;

public class BasketManager {

  private final BehaviorRelay<Map<String, Integer>> relay = BehaviorRelay.create();

  private final Map<String, Integer> basketEntries = new HashMap<>();

  private final BasketDbRepository basketDbRepository;

  @Inject
  public BasketManager(BasketDbRepository basketDbRepository) {
    this.basketDbRepository = basketDbRepository;
  }

  public void basketEntryChanged(String menuItemId, int newAmount) {
    if (newAmount == 0) {
      basketEntries.remove(menuItemId);
      deleteBasketItem(menuItemId);
    } else {
      basketEntries.put(menuItemId, newAmount);
      insertBasketItem(menuItemId, newAmount);
    }
    relay.call(basketEntries);
  }

  public BehaviorRelay<Map<String, Integer>> getRelay() {
    return relay;
  }

  private void insertBasketItem(String menuItemId, int newAmount) {
    basketDbRepository.insertBasketItem(menuItemId, newAmount).subscribeOn(Schedulers.io())
        .subscribe();
  }

  private void deleteBasketItem(String menuItemId) {
    basketDbRepository.deleteBasketItem(menuItemId).subscribeOn(Schedulers.io())
        .subscribe();
  }
}
