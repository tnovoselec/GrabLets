package com.grablets.di;


import android.app.Application;
import android.content.Context;

import com.grablets.GrabLetsApplication;
import com.grablets.api.GrabLetsClient;
import com.grablets.business.NotificationScheduler;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.module.ApplicationModule;
import com.grablets.di.module.DatabaseModule;
import com.grablets.di.module.GrabLetsApiModule;
import com.grablets.di.module.RepositoryModule;
import com.grablets.di.qualifier.ForApplication;
import com.grablets.receiver.AlarmReceiver;
import com.grablets.receiver.BootBroadcastReceiver;
import com.grablets.repository.RestaurantsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        ApplicationModule.class,
        GrabLetsApiModule.class,
        DatabaseModule.class,
        RepositoryModule.class
    }
)
public interface ApplicationComponent {

  final class Initializer {
    static public ApplicationComponent init(GrabLetsApplication application) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .databaseModule(new DatabaseModule(application))
          .build();
    }

    // No instances
    private Initializer() {
    }
  }

  @ForApplication
  Context provideApplicationContext();

  Application getApplication();

  PreferenceAccessor getPreferenceAccessor();

  NotificationScheduler getNotificationScheduler();

  GrabLetsClient getGrabLetsClient();

  RestaurantsRepository getRestaurantsRepository();

  void inject(GrabLetsApplication commerceApplication);

  void inject(BootBroadcastReceiver bootBroadcastReceiver);

  void inject(AlarmReceiver alarmReceiver);
}
