package com.grablets.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;

import rx.Observable;

public class RestaurantsDao extends Dao {

  @Override
  public void createTable(SQLiteDatabase database) {

    CREATE_TABLE(DbRestaurant.TABLE_NAME,
        DbRestaurant.COL_ID + " TEXT PRIMARY KEY NOT NULL",
        DbRestaurant.COL_TITLE + " TEXT",
        DbRestaurant.COL_DESCRIPTION + " TEXT",
        DbRestaurant.COL_IMAGE_URL + " TEXT")
        .execute(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public Observable<List<DbRestaurant>> getRestaurants() {
    return query(
        SELECT(
            DbRestaurant.COL_ID,
            DbRestaurant.COL_TITLE,
            DbRestaurant.COL_DESCRIPTION,
            DbRestaurant.COL_IMAGE_URL)
            .FROM(DbRestaurant.TABLE_NAME))
        .run()
        .mapToList(DbRestaurantMapper.MAPPER);
  }

  public void insert(List<DbRestaurant> restaurants) {
    for (DbRestaurant restaurant : restaurants) {
      ContentValues values = DbRestaurantMapper.contentValues()
          .id(restaurant.getId())
          .title(restaurant.getTitle())
          .description(restaurant.getDescription())
          .imageUrl(restaurant.getImageUrl())
          .build();
      Observable<Long> insert = insert(DbRestaurant.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
  }
}
