package com.grablets.mvp;

public class RegistrationMvp {

  public interface Presenter {
    void register(String firstName, String lastName, String phone, String address, String additionalInfo);
  }

  public interface View extends MvpView {

  }
}
