package com.grablets.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.LoginMvp;
import com.grablets.mvp.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginMvp.View {

  public static final String TAG = LoginFragment.class.getSimpleName();

  @BindView(R.id.email_field)
  EditText emailField;

  @BindView(R.id.password_field)
  EditText passwordField;

  @Inject
  LoginPresenter loginPresenter;

  public static LoginFragment create() {
    return new LoginFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_login, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    loginPresenter.activate();
    loginPresenter.attachView(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    loginPresenter.deactivate();
    loginPresenter.detachView();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @OnClick(R.id.login_button)
  public void onLoginClicked() {
    loginPresenter.onLoginClicked(emailField.getText().toString(), passwordField.getText().toString());
  }

  @OnClick(R.id.register)
  public void onRegistrationClicked(){
    loginPresenter.onRegistrationClicked();
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }
}
