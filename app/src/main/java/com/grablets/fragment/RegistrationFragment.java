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
import com.grablets.mvp.RegistrationMvp;
import com.grablets.mvp.presenter.RegistrationPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrationFragment extends BaseFragment implements RegistrationMvp.View {

  public static final String TAG = RegistrationFragment.class.getSimpleName();

  @BindView(R.id.user_first_name_label)
  EditText userFirstNameLabel;
  @BindView(R.id.user_last_name_label)
  EditText userLastNameLabel;
  @BindView(R.id.user_phone_label)
  EditText userPhoneLabel;
  @BindView(R.id.user_address_label)
  EditText userAddressLabel;
  @BindView(R.id.user_additional_info_label)
  EditText userAdditionalInfoLabel;

  @Inject
  RegistrationPresenter registrationPresenter;

  public static RegistrationFragment create() {
    return new RegistrationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_registration, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    registrationPresenter.activate();
    registrationPresenter.attachView(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    registrationPresenter.deactivate();
    registrationPresenter.detachView();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @OnClick(R.id.user_register)
  public void onRegisterClicked() {
    registrationPresenter.register(
        userFirstNameLabel.getText().toString(),
        userLastNameLabel.getText().toString(),
        userPhoneLabel.getText().toString(),
        userAddressLabel.getText().toString(),
        userAdditionalInfoLabel.getText().toString()
    );
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }
}
