package com.grablets.mvp.presenter;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.grablets.R;
import com.grablets.Router;
import com.grablets.api.FirebaseAuthClient;
import com.grablets.business.PreferenceAccessor;
import com.grablets.mvp.LoginMvp;

import javax.inject.Inject;

import rx.functions.Action1;

public class LoginPresenter extends SubscribingPresenter<LoginMvp.View> implements LoginMvp.Presenter {

  private final Router router;
  private final FirebaseAuthClient firebaseAuthClient;
  private final PreferenceAccessor preferenceAccessor;
  private final AuthHandler authHandler;

  @Inject
  public LoginPresenter(Router router, FirebaseAuthClient firebaseAuthClient, PreferenceAccessor preferenceAccessor) {
    this.router = router;
    this.firebaseAuthClient = firebaseAuthClient;
    this.preferenceAccessor = preferenceAccessor;
    this.authHandler = new AuthHandler();
  }

  @Override
  public void activate() {
    super.activate();
    firebaseAuthClient.activateClient();
  }

  @Override
  public void deactivate() {
    super.deactivate();
    firebaseAuthClient.deactivateClient();
  }

  @Override
  public void onLoginClicked(String email, String password) {
    firebaseAuthClient.getFirebaseAuth().subscribe(authHandler);
    firebaseAuthClient.loginUser(email, password);
  }

  @Override
  public void onRegistrationClicked() {
    router.showRegistrationActivity();
  }

  private void onFirebaseAuthResult(FirebaseAuth firebaseAuthResult) {

    if (firebaseAuthResult != null && firebaseAuthResult.getCurrentUser() != null) {
      Log.e("Loginpresenter", firebaseAuthResult.toString());
      if (!preferenceAccessor.isUserLoggedIn()) {
        preferenceAccessor.setUserLoggedIn(true);
        router.showMainActivity();
        router.finishCurrentActivity();
      }
    } else {
      if (firebaseAuthResult == null) {
        Log.e("Loginpresenter", "null");
      } else if (firebaseAuthResult.getCurrentUser() == null) {
        Log.e("Loginpresenter", "user null");
      }
      if (isViewAttached()) {
        getView().showErrorMessage(R.string.login_error_message);
      }
    }
  }

  private class AuthHandler implements Action1<FirebaseAuth>{

    @Override
    public void call(FirebaseAuth firebaseAuth) {
      onFirebaseAuthResult(firebaseAuth);
    }
  }
}
