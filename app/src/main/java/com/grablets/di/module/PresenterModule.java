package com.grablets.di.module;

import com.grablets.di.scope.ActivityScope;
import com.grablets.mvp.presenter.DailyMenuPresenter;
import com.grablets.mvp.presenter.RestaurantsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @ActivityScope
  DailyMenuPresenter dailyMenuPresenter(){
    return new DailyMenuPresenter();
  }

  @Provides
  @ActivityScope
  RestaurantsPresenter restaurantsPresenter(){
    return new RestaurantsPresenter();
  }
}
