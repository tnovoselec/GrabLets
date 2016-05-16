package com.grablets.di.module;

import android.content.Context;

import com.grablets.db.RestaurantMenuItemDao;
import com.grablets.db.RestaurantsDao;
import com.grablets.di.qualifier.ForApplication;
import com.hannesdorfmann.sqlbrite.dao.DaoManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

  private static final int DB_VERSION = 2;
  private static final String DB_NAME = "GrabLets.db";

  private final Context context;

  @Inject
  public DatabaseModule(@ForApplication Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  public RestaurantsDao provideRestaurantsDao() {
    return new RestaurantsDao();
  }

  @Provides
  @Singleton
  public RestaurantMenuItemDao provideRestaurantMenuItemDao() {
    return new RestaurantMenuItemDao();
  }

  @Provides
  @Singleton
  public DaoManager provideDaoManager(RestaurantsDao restaurantsDao, RestaurantMenuItemDao restaurantMenuItemDao) {
    return DaoManager.with(context).databaseName(DB_NAME)
        .version(DB_VERSION)
        .add(restaurantsDao)
        .add(restaurantMenuItemDao)
        .logging(true)
        .build();
  }
}
