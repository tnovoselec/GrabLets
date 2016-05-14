package com.grablets.activity;

import android.os.Bundle;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.LoginFragment;

public class LoginActivity extends BaseActivity {

  private LoginFragment loginFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);

    if (savedInstanceState == null) {
      loginFragment = LoginFragment.create();
      getSupportFragmentManager().beginTransaction().replace(R.id.login_container, loginFragment, LoginFragment.TAG).commit();
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }
}
