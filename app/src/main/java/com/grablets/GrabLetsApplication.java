package com.grablets;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.grablets.di.ApplicationComponent;
import com.grablets.di.ComponentFactory;
import com.grablets.di.ComponentProvider;

public class GrabLetsApplication extends Application implements ComponentProvider<ApplicationComponent> {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    MultiDex.install(this);
    inject();

    Stetho.initializeWithDefaults(this);
  }

  protected void inject() {
    applicationComponent = ComponentFactory.createApplicationComponent(this);
    applicationComponent.inject(this);
  }


  @Override
  public ApplicationComponent getComponent() {
    return applicationComponent;
  }

}
