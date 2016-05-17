package com.grablets.business;

import com.jakewharton.rxrelay.BehaviorRelay;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class BasketManager {

  private final BehaviorRelay<Map<String, Integer>> relay = BehaviorRelay.create();

  private final Map<String, Integer> basketEntries = new HashMap<>();

  @Inject
  public BasketManager() {
  }

  public void basketEntryChanged(String menuItemId, int newAmount) {
    if (newAmount == 0) {
      basketEntries.remove(menuItemId);
    } else {
      basketEntries.put(menuItemId, newAmount);
    }
    relay.call(basketEntries);
  }

  public BehaviorRelay<Map<String, Integer>> getRelay() {
    return relay;
  }
}
