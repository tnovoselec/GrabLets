package com.grablets.api;

import com.grablets.api.model.ApiRestaurant;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface GrabLetsApi {

  @GET("/restaurants.json")
  Observable<List<ApiRestaurant>> getRestaurants();

}