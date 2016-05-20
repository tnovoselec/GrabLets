package com.grablets.di;

import com.grablets.activity.CheckoutActivity;
import com.grablets.activity.LoginActivity;
import com.grablets.activity.MainActivity;
import com.grablets.activity.RegistrationActivity;
import com.grablets.activity.RestaurantMenuActivity;
import com.grablets.activity.SettingsActivity;
import com.grablets.fragment.CheckoutFragment;
import com.grablets.fragment.DailyMenuFragment;
import com.grablets.fragment.LoginFragment;
import com.grablets.fragment.RegistrationFragment;
import com.grablets.fragment.RestaurantMenuFragment;
import com.grablets.fragment.RestaurantsFragment;
import com.grablets.fragment.SettingsFragment;

public interface ActivityComponentInjects {

  void inject(MainActivity mainActivity);

  void inject(DailyMenuFragment dailyMenuFragment);

  void inject(RestaurantsFragment restaurantsFragment);

  void inject(LoginActivity loginActivity);

  void inject(LoginFragment loginFragment);

  void inject(RegistrationActivity registrationActivity);

  void inject(RegistrationFragment registrationFragment);

  void inject(SettingsActivity settingsActivity);

  void inject(SettingsFragment settingsFragment);

  void inject(CheckoutActivity checkoutActivity);

  void inject(CheckoutFragment checkoutFragment);

  void inject(RestaurantMenuActivity restaurantMenuActivity);

  void inject(RestaurantMenuFragment restaurantMenuFragment);
}
