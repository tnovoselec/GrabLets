package com.grablets.mvp;

public class RegistrationMvp {

  public interface Presenter {
    void register(String email, String password,String firstName, String lastName, String phone, String address, String additionalInfo);
  }

  public interface View extends MvpView {

  }
}
