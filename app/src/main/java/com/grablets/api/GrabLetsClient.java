package com.grablets.api;

import java.util.List;

import com.grablets.api.model.ApiRestaurant;
import com.grablets.api.model.LoginRequest;
import com.grablets.api.model.LoginResponse;
import com.grablets.api.model.RegisterRequest;
import com.grablets.api.model.RegisterResponse;

import rx.Observable;

public class GrabLetsClient {

  private final GrabLetsApi grabLetsApi;

  public GrabLetsClient(GrabLetsApi grabLetsApi) {
    this.grabLetsApi = grabLetsApi;
  }

  public Observable<List<ApiRestaurant>> getRestaurants() {
    return grabLetsApi.getRestaurants();//.map(response -> response.restaurants);
  }

  public Observable<LoginResponse> login(LoginRequest loginRequest) {
    return grabLetsApi.login(loginRequest);
  }

  public Observable<RegisterResponse> register(RegisterRequest registerRequest){
    return grabLetsApi.register(registerRequest);
  }
}
