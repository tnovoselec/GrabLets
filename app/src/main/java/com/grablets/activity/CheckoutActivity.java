package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.CheckoutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutActivity extends BaseActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  public static Intent createIntent(Context context){
    return new Intent(context, CheckoutActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checkout);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.checkout_container, CheckoutFragment.create(), CheckoutFragment.TAG)
          .commit();
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }
}
