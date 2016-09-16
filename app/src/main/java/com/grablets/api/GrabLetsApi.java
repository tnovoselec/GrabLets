package com.grablets.api;

import java.util.List;

import com.grablets.api.model.ApiRestaurant;
import com.grablets.api.model.LoginRequest;
import com.grablets.api.model.LoginResponse;
import com.grablets.api.model.RegisterRequest;
import com.grablets.api.model.RegisterResponse;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface GrabLetsApi {
  //  http://46.101.111.210/offers.php
  //  @GET("/api/restaurants")
  //  Observable<ApiRestaurantsResponse> getRestaurants();

  @GET("/offers.php")
  Observable<List<ApiRestaurant>> getRestaurants();
  //  Observable<ApiRestaurantsResponse> getRestaurants();

  @POST("/profilecheck.php")
  Observable<LoginResponse> login(@Body LoginRequest loginRequest);

  @POST("/profilecreate.php")
  Observable<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
