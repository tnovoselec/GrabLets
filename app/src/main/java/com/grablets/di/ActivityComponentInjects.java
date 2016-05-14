package com.grablets.di;

import com.grablets.activity.MainActivity;
import com.grablets.fragment.DailyMenuFragment;
import com.grablets.fragment.RestaurantsFragment;

public interface ActivityComponentInjects {

  void inject(MainActivity mainActivity);

  void inject(DailyMenuFragment dailyMenuFragment);

  void inject(RestaurantsFragment restaurantsFragment);
}
