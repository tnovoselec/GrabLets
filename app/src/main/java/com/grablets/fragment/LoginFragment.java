package com.grablets.fragment;

import java.util.regex.Pattern;

import javax.inject.Inject;

import com.afollestad.materialdialogs.MaterialDialog;
import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.LoginMvp;
import com.grablets.mvp.presenter.LoginPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;

public class LoginFragment extends BaseFragment implements LoginMvp.View {

  public static final String TAG = LoginFragment.class.getSimpleName();

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
  public static final int MINIMAL_PASSWORD_LENGTH = 4;

  @BindView(R.id.email_field)
  EditText emailField;

  @BindView(R.id.password_field)
  EditText passwordField;

  @BindView(R.id.login_button)
  Button loginButton;

  @Inject
  LoginPresenter loginPresenter;

  private MaterialDialog progressDialog;

  public static LoginFragment create() {
    return new LoginFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_login, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Observable.combineLatest(
        observeEmailField(),
        observePasswordField(),
        (isEmailValid, isPasswordValid) -> isEmailValid && isPasswordValid)
        .subscribe(enabled -> {
          loginButton.setEnabled(enabled);
        });

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
  public void onRegistrationClicked() {
    loginPresenter.onRegistrationClicked();
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }

  private Observable<Boolean> observeEmailField() {
    return RxTextView.textChanges(emailField)
        .map(charSequence -> EMAIL_PATTERN.matcher(charSequence).matches())
        .distinctUntilChanged();
  }

  private Observable<Boolean> observePasswordField() {
    return RxTextView.textChanges(passwordField)
        .map(charSequence -> charSequence.length() >= MINIMAL_PASSWORD_LENGTH)
        .distinctUntilChanged();
  }

  @Override
  public void showProgress() {
    progressDialog = new MaterialDialog.Builder(getContext())
        .title(R.string.login_in_progress)
        .cancelable(false)
        .content(R.string.please_wait)
        .widgetColor(Color.BLACK)
        .progress(true, 1)
        .build();
    progressDialog.show();
  }

  @Override
  public void hideProgress() {
    progressDialog.dismiss();
  }
}
