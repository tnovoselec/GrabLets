package com.grablets;

import android.content.Context;

import com.grablets.activity.CheckoutActivity;
import com.grablets.activity.MainActivity;
import com.grablets.activity.RegistrationActivity;
import com.grablets.activity.SettingsActivity;
import com.grablets.di.qualifier.ForActivity;

import javax.inject.Inject;

public class Router {

  private final Context context;

  @Inject
  public Router(@ForActivity Context context) {
    this.context = context;
  }

  public void showRegistrationActivity() {
    context.startActivity(RegistrationActivity.createIntent(context));
  }

  public void showMainActivity() {
    context.startActivity(MainActivity.createIntent(context));
  }

  public void showSettingsActivity() {
    context.startActivity(SettingsActivity.createIntent(context));
  }

  public void showCheckoutActivity(){
    context.startActivity(CheckoutActivity.createIntent(context));
  }
}
