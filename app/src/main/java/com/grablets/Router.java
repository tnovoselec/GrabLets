package com.grablets;

import android.app.Activity;

import com.grablets.activity.CheckoutActivity;
import com.grablets.activity.LoginActivity;
import com.grablets.activity.MainActivity;
import com.grablets.activity.RegistrationActivity;
import com.grablets.activity.SettingsActivity;

import javax.inject.Inject;

public class Router {

  private final Activity context;

  @Inject
  public Router(Activity context) {
    this.context = context;
  }

  public void showLoginActivity() {
    context.startActivity(LoginActivity.createIntent(context));
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

  public void showCheckoutActivity() {
    context.startActivity(CheckoutActivity.createIntent(context));
  }

  public void finishCurrentActivity() {
    context.finish();
  }
}
