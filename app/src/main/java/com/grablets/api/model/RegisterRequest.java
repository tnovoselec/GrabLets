package com.grablets.api.model;

public class RegisterRequest {

  public final String email;
  public final String password;
  public final String firstName;
  public final String lastName;
  public final String phone;
  public final String addressHome;
  public final String addressWork;
  public final String additionalInfo;

  public RegisterRequest(String email, String password, String firstName,
      String lastName, String phone, String addressHome, String addressWork, String additionalInfo) {
    this.additionalInfo = additionalInfo;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.addressHome = addressHome;
    this.addressWork = addressWork;
  }
}
