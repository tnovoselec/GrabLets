package com.grablets.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.db.model.DbRestaurantMenuItemMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;

import rx.Observable;

public class RestaurantMenuItemDao extends Dao {
  @Override
  public void createTable(SQLiteDatabase database) {
    CREATE_TABLE(DbRestaurantMenuItem.TABLE_NAME,
        DbRestaurantMenuItem.COL_ID + " TEXT PRIMARY KEY NOT NULL",
        DbRestaurantMenuItem.COL_TITLE + " TEXT",
        DbRestaurantMenuItem.COL_DESCRIPTION + " TEXT",
        DbRestaurantMenuItem.COL_IMAGE_URL + " TEXT")
        .execute(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public Observable<List<DbRestaurantMenuItem>> getMenuItemsForRestaurant(String restaurantId) {
    return query(
        SELECT(
            DbRestaurantMenuItem.COL_ID,
            DbRestaurantMenuItem.COL_RESTAURANT_ID,
            DbRestaurantMenuItem.COL_TITLE,
            DbRestaurantMenuItem.COL_DESCRIPTION,
            DbRestaurantMenuItem.COL_IMAGE_URL)
            .FROM(DbRestaurantMenuItem.TABLE_NAME)
            .WHERE(DbRestaurantMenuItem.COL_RESTAURANT_ID + "=" + restaurantId))
        .run()
        .mapToList(DbRestaurantMenuItemMapper.MAPPER);
  }

  public void insert(List<DbRestaurantMenuItem> restaurantMenuItems) {
    for (DbRestaurantMenuItem restaurantMenuItem : restaurantMenuItems) {
      ContentValues values = DbRestaurantMenuItemMapper.contentValues()
          .id(restaurantMenuItem.getId())
          .restaurantId(restaurantMenuItem.getRestaurantId())
          .title(restaurantMenuItem.getTitle())
          .description(restaurantMenuItem.getDescription())
          .imageUrl(restaurantMenuItem.getImageUrl())
          .build();
      Observable<Long> insert = insert(DbRestaurantMenuItem.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
  }
}
