package com.grablets.api.model;

public class LoginRequest {

  public final String email;
  public final String pin;

  public LoginRequest(String email, String pin) {
    this.email = email;
    this.pin = pin;
  }
}
