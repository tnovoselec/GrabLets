package com.grablets.interactor;

import com.grablets.api.GrabLetsClient;
import com.grablets.api.model.RegisterRequest;
import com.grablets.api.model.RegisterResponse;

import rx.Observable;

public class RegisterUseCase implements UseCaseWithParameter<RegisterResponse, RegisterRequest>  {

  private final GrabLetsClient grabLetsClient;

  public RegisterUseCase(GrabLetsClient grabLetsClient) {
    this.grabLetsClient = grabLetsClient;
  }

  @Override
  public Observable<RegisterResponse> execute(RegisterRequest registerRequest) {
    return grabLetsClient.register(registerRequest);
  }
}
