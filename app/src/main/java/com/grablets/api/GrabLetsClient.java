package com.grablets.api;

import com.grablets.api.model.ApiRestaurant;

import java.util.List;

import rx.Observable;

public class GrabLetsClient {

  private final GrabLetsApi grabLetsApi;

  public GrabLetsClient(GrabLetsApi grabLetsApi) {
    this.grabLetsApi = grabLetsApi;
  }

  public Observable<List<ApiRestaurant>> getRestaurants() {
    return grabLetsApi.getRestaurants().map(response -> response.restaurants);
  }
}
