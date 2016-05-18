package com.grablets.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grablets.db.model.DbRestaurant;
import com.grablets.db.model.DbRestaurantMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;

import rx.Completable;
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
    DROP_TABLE_IF_EXISTS(DbRestaurant.TABLE_NAME).execute(db);
    createTable(db);
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

  public Completable insert(List<DbRestaurant> restaurants) {
    return Completable.fromAction(() -> insertRestaurants(restaurants));
  }

  private void insertRestaurants(List<DbRestaurant> restaurants) {
    for (DbRestaurant restaurant : restaurants) {
      ContentValues values = DbRestaurantMapper.contentValues()
          .id(restaurant.getId())
          .title(restaurant.getTitle())
          .description(restaurant.getDescription())
          .imageUrl(restaurant.getImageUrl())
          .build();
      db.insert(DbRestaurant.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public Completable delete() {
    return Completable.fromAction(() ->
        db.delete(DbRestaurant.TABLE_NAME, null));
  }
}
