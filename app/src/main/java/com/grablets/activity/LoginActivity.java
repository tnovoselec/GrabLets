package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.grablets.R;
import com.grablets.Router;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.LoginFragment;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

  @Inject
  PreferenceAccessor preferenceAccessor;

  @Inject
  Router router;

  private LoginFragment loginFragment;

  public static Intent createIntent(Context context){
    Intent intent = new Intent(context, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (preferenceAccessor.isUserLoggedIn()){
      router.showMainActivity();
      router.finishCurrentActivity();
      return;
    }

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
