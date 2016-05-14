package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.RegistrationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends BaseActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private RegistrationFragment registrationFragment;

  public static Intent createIntent(Context context){
    return new Intent(context, RegistrationActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    if (savedInstanceState == null){
      registrationFragment = RegistrationFragment.create();
      getSupportFragmentManager().beginTransaction().replace(R.id.registration_container, registrationFragment, RegistrationFragment.TAG).commit();
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }
}
