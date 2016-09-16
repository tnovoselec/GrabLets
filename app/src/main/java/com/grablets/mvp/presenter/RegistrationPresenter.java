package com.grablets.mvp.presenter;

import javax.inject.Inject;

import com.grablets.Router;
import com.grablets.api.model.RegisterRequest;
import com.grablets.api.model.RegisterResponse;
import com.grablets.interactor.RegisterUseCase;
import com.grablets.mvp.RegistrationMvp;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegistrationPresenter extends SubscribingPresenter<RegistrationMvp.View>
    implements RegistrationMvp.Presenter {

  private final RegisterUseCase registerUseCase;
  private final Router router;

  @Inject
  public RegistrationPresenter(Router router, RegisterUseCase registerUseCase) {
    this.router = router;
    this.registerUseCase = registerUseCase;
  }

  @Override
  public void register(String email, String password, String firstName, String lastName, String phone,
      String addressHome, String addressWork,
      String additionalInfo) {

    addSubscription(registerUseCase.execute(new RegisterRequest(
        email,
        password,
        firstName,
        lastName,
        phone,
        addressHome,
        addressWork,
        additionalInfo
    )).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::onRegistrationSuccess, this::onRegistrationFailed
        ));
  }

  private void onRegistrationSuccess(RegisterResponse registerResponse) {
    switch (registerResponse.id) {
      case 0:
        onRegistrationFailed(new RuntimeException());
        return;
      case 6:
        return;
      default:
        return;
    }
  }

  private void onRegistrationFailed(Throwable throwable) {
    throwable.printStackTrace();
  }
}
