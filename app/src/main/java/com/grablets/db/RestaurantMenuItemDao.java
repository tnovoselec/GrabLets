package com.grablets.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.grablets.db.model.DbRestaurantMenuItem;
import com.grablets.db.model.DbRestaurantMenuItemMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;
import java.util.Set;

import rx.Completable;
import rx.Observable;

public class RestaurantMenuItemDao extends Dao {

  @Override
  public void createTable(SQLiteDatabase database) {
    CREATE_TABLE(DbRestaurantMenuItem.TABLE_NAME,
        DbRestaurantMenuItem.COL_ID + " TEXT PRIMARY KEY NOT NULL",
        DbRestaurantMenuItem.COL_RESTAURANT_ID + " TEXT",
        DbRestaurantMenuItem.COL_TITLE + " TEXT",
        DbRestaurantMenuItem.COL_DESCRIPTION + " TEXT",
        DbRestaurantMenuItem.COL_PRICE + " FLOAT",
        DbRestaurantMenuItem.COL_IMAGE_URL + " TEXT")
        .execute(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    DROP_TABLE_IF_EXISTS(DbRestaurantMenuItem.TABLE_NAME).execute(db);
    createTable(db);
  }

  public Observable<List<DbRestaurantMenuItem>> getMenuItemsForRestaurant(String restaurantId) {
    return query(
        SELECT(
            DbRestaurantMenuItem.COL_ID,
            DbRestaurantMenuItem.COL_RESTAURANT_ID,
            DbRestaurantMenuItem.COL_TITLE,
            DbRestaurantMenuItem.COL_DESCRIPTION,
            DbRestaurantMenuItem.COL_PRICE,
            DbRestaurantMenuItem.COL_IMAGE_URL)
            .FROM(DbRestaurantMenuItem.TABLE_NAME)
            .WHERE(DbRestaurantMenuItem.COL_RESTAURANT_ID + "=" + restaurantId))
        .run()
        .mapToList(DbRestaurantMenuItemMapper.MAPPER);
  }

  public Observable<List<DbRestaurantMenuItem>> getMenuItemsByIds(Set<String> ids) {
    final String idsClause ="('" + TextUtils.join("','", ids) + "')";
    return query(
        SELECT(
            DbRestaurantMenuItem.COL_ID,
            DbRestaurantMenuItem.COL_RESTAURANT_ID,
            DbRestaurantMenuItem.COL_TITLE,
            DbRestaurantMenuItem.COL_DESCRIPTION,
            DbRestaurantMenuItem.COL_PRICE,
            DbRestaurantMenuItem.COL_IMAGE_URL)
            .FROM(DbRestaurantMenuItem.TABLE_NAME)
            .WHERE(DbRestaurantMenuItem.COL_ID + " IN " + idsClause))
        .run()
        .mapToList(DbRestaurantMenuItemMapper.MAPPER);
  }

  public Completable insert(List<DbRestaurantMenuItem> restaurantMenuItems) {
    return Completable.fromAction(() -> insertRestaurantMenuItems(restaurantMenuItems));
  }

  private void insertRestaurantMenuItems(List<DbRestaurantMenuItem> restaurantMenuItems) {
    for (DbRestaurantMenuItem restaurantMenuItem : restaurantMenuItems) {
      ContentValues values = DbRestaurantMenuItemMapper.contentValues()
          .id(restaurantMenuItem.getId())
          .restaurantId(restaurantMenuItem.getRestaurantId())
          .title(restaurantMenuItem.getTitle())
          .description(restaurantMenuItem.getDescription())
          .imageUrl(restaurantMenuItem.getImageUrl())
          .build();
      db.insert(DbRestaurantMenuItem.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public Completable delete() {
    return Completable.fromAction(() ->
        db.delete(DbRestaurantMenuItem.TABLE_NAME, null));
  }
}
