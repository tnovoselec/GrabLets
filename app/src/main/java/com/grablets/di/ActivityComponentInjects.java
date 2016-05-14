package com.grablets.di;

import com.grablets.activity.MainActivity;
import com.grablets.fragment.DailyMenuFragment;

public interface ActivityComponentInjects {

  void inject(MainActivity mainActivity);

  void inject(DailyMenuFragment dailyMenuFragment);
}
