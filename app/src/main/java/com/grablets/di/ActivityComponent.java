package com.grablets.di;


import android.content.Context;

import com.grablets.Router;
import com.grablets.activity.BaseActivity;
import com.grablets.di.module.ActivityModule;
import com.grablets.di.qualifier.ForActivity;
import com.grablets.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
    dependencies = {
        ApplicationComponent.class
    },
    modules = {
        ActivityModule.class
    }
)
public interface ActivityComponent extends ApplicationComponent, ActivityComponentInjects{

  final class Initializer {

    static public ActivityComponent init(BaseActivity injectorActivity,
                                         ComponentProvider<ApplicationComponent> applicationComponentProvider) {
      return DaggerActivityComponent.builder()
          .applicationComponent(applicationComponentProvider.getComponent())
          .activityModule(new ActivityModule(injectorActivity))
          .build();
    }



    // No instances
    private Initializer() {
    }
  }
  @ForActivity
  Context provideActivityContext();

  Router router();
}
