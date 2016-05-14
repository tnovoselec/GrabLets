package com.grablets.di;


import android.app.Application;

import com.grablets.GrabLetsApplication;
import com.grablets.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        ApplicationModule.class,
    }
)
public interface ApplicationComponent {

  /**
   * An initializer that creates the graph from an application.
   */
  final class Initializer {
    static public ApplicationComponent init(GrabLetsApplication application) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .build();
    }

    // No instances
    private Initializer() {
    }
  }

  Application getApplication();

  void inject(GrabLetsApplication commerceApplication);

}
