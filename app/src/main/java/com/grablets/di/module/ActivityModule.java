package com.grablets.di.module;


import android.app.Activity;
import android.content.Context;

import com.grablets.Router;
import com.grablets.activity.BaseActivity;
import com.grablets.di.qualifier.ForActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  @Provides
  @ForActivity
  Router router() {
    return new Router(activity);
  }

  @Provides
  @ForActivity
  Context context(){
    return activity;
  }

  @Provides
  Activity activity(){
    return activity;
  }
}
