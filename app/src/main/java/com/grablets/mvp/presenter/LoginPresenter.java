package com.grablets.mvp.presenter;

import javax.inject.Inject;

import com.grablets.R;
import com.grablets.Router;
import com.grablets.api.model.LoginRequest;
import com.grablets.api.model.LoginResponse;
import com.grablets.interactor.LoginUseCase;
import com.grablets.mvp.LoginMvp;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends SubscribingPresenter<LoginMvp.View> implements LoginMvp.Presenter {

  private final Router router;
  private final LoginUseCase loginUseCase;

  @Inject
  public LoginPresenter(Router router, LoginUseCase loginUseCase) {
    this.router = router;
    this.loginUseCase = loginUseCase;
  }

  @Override
  public void onLoginClicked(String email, String password) {

    if (isViewAttached()){
      getView().showProgress();
    }

    addSubscription(loginUseCase.execute(new LoginRequest(email, password))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::onLoginSuccess, this::onLoginError));
  }

  private void onLoginSuccess(LoginResponse loginResponse) {
    if (isViewAttached()){
      getView().hideProgress();
    }
    router.showMainActivity();
  }

  private void onLoginError(Throwable throwable) {
    if (isViewAttached()){
      getView().hideProgress();
      getView().showErrorMessage(R.string.server_error_message);
    }
    throwable.printStackTrace();
  }

  @Override
  public void onRegistrationClicked() {
    router.showRegistrationActivity();
  }
}
