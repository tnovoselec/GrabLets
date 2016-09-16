package com.grablets.interactor;

import com.grablets.api.GrabLetsClient;
import com.grablets.api.model.LoginRequest;
import com.grablets.api.model.LoginResponse;

import rx.Observable;

public class LoginUseCase implements UseCaseWithParameter<LoginResponse, LoginRequest> {

  private final GrabLetsClient grabLetsClient;

  public LoginUseCase(GrabLetsClient grabLetsClient) {
    this.grabLetsClient = grabLetsClient;
  }

  @Override
  public Observable<LoginResponse> execute(LoginRequest loginRequest) {
    return grabLetsClient.login(loginRequest);
  }
}
