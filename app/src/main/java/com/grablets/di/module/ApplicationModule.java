package com.grablets.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.grablets.business.BasketManager;
import com.grablets.business.NotificationScheduler;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.qualifier.ForApplication;
import com.grablets.repository.BasketDbRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public Application provideApplication() {
    return application;
  }


  @Provides
  @Singleton
  @ForApplication
  Context provideApplicationContext() {
    return application;
  }

  @Provides
  @Singleton
  protected SharedPreferences getDefaultSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides
  @Singleton
  protected PreferenceAccessor preferenceAccessor(SharedPreferences sharedPreferences) {
    return new PreferenceAccessor(sharedPreferences);
  }

  @Provides
  @Singleton
  protected NotificationScheduler notificationScheduler(@ForApplication Context context, PreferenceAccessor preferenceAccessor) {
    return new NotificationScheduler(context, preferenceAccessor);
  }

  @Provides
  @Singleton
  protected BasketManager basketManager(BasketDbRepository basketDbRepository){
    return new BasketManager(basketDbRepository);
  }
}