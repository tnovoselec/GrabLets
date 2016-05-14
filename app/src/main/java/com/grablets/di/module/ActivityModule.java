package com.grablets.di.module;


import com.grablets.activity.BaseActivity;

import dagger.Module;

@Module
public class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }
}
