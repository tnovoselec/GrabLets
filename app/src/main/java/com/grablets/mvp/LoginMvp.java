package com.grablets.mvp;

public class LoginMvp {

  public interface Presenter {
    void onLoginClicked(String email, String password);

    void onRegistrationClicked();
  }

  public interface View extends MvpView {

  }
}
