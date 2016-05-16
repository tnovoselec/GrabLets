package com.grablets.api;

import com.grablets.api.model.ApiRestaurantsResponse;

import retrofit.http.GET;
import rx.Observable;

public interface GrabLetsApi {

  @GET("/api/restaurants")
  Observable<ApiRestaurantsResponse> getRestaurants();
}
