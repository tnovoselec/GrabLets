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

  private static final int DB_VERSION = 3;
  private static final String DB_NAME = "GrabLets.db";

  private final Context context;
  private final DaoManager daoManager;

  private final RestaurantsDao restaurantsDao;
  private final RestaurantMenuItemDao restaurantMenuItemDao;

  @Inject
  public DatabaseModule(@ForApplication Context context) {
    this.context = context;
    this.restaurantsDao = new RestaurantsDao();
    this.restaurantMenuItemDao = new RestaurantMenuItemDao();
    this.daoManager = DaoManager.with(context).databaseName(DB_NAME)
        .version(DB_VERSION)
        .add(restaurantsDao)
        .add(restaurantMenuItemDao)
        .logging(true)
        .build();
  }

  @Provides
  @Singleton
  public RestaurantsDao provideRestaurantsDao() {
    return restaurantsDao;
  }

  @Provides
  @Singleton
  public RestaurantMenuItemDao provideRestaurantMenuItemDao() {
    return restaurantMenuItemDao;
  }
}
