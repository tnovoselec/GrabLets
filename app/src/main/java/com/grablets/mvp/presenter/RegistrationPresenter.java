package com.grablets.mvp.presenter;

import com.grablets.Router;
import com.grablets.mvp.RegistrationMvp;

import javax.inject.Inject;

public class RegistrationPresenter extends SubscribingPresenter<RegistrationMvp.View> implements RegistrationMvp.Presenter {

  private final Router router;

  @Inject
  public RegistrationPresenter(Router router) {
    this.router = router;
  }

  @Override
  public void register(String email, String password, String firstName, String lastName, String phone, String address, String additionalInfo) {
    router.showMainActivity();
  }
}
