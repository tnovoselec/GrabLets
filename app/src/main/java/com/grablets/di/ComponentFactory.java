package com.grablets.di;


import com.grablets.GrabLetsApplication;
import com.grablets.activity.BaseActivity;

public final class ComponentFactory {

  public static ApplicationComponent createApplicationComponent(GrabLetsApplication application) {
    return ApplicationComponent.Initializer.init(application);
  }

  public static ActivityComponent createActivityComponent(BaseActivity injectorActivity,
                                                          ComponentProvider<ApplicationComponent> applicationComponentProvider) {
    return ActivityComponent.Initializer.init(injectorActivity, applicationComponentProvider);
  }
}