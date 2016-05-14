package com.grablets.mvp.presenter;

import com.grablets.Router;
import com.grablets.mvp.LoginMvp;

import javax.inject.Inject;

public class LoginPresenter extends SubscribingPresenter<LoginMvp.View> implements LoginMvp.Presenter {

  private final Router router;

  @Inject
  public LoginPresenter(Router router) {
    this.router = router;
  }

  @Override
  public void onLoginClicked(String email, String password) {
    router.showMainActivity();
  }

  @Override
  public void onRegistrationClicked() {
    router.showRegistrationActivity();
  }
}
