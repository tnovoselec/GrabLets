package com.grablets.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grablets.db.model.DbBasketItem;
import com.grablets.db.model.DbBasketItemMapper;
import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;

import rx.Completable;
import rx.Observable;

public class BasketItemDao extends Dao {

  @Override
  public void createTable(SQLiteDatabase database) {
    CREATE_TABLE(DbBasketItem.TABLE_NAME,
        DbBasketItem.COL_MENU_ITEM_ID + " TEXT PRIMARY KEY NOT NULL",
        DbBasketItem.COL_AMOUNT + " INTEGER")
        .execute(database);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    DROP_TABLE_IF_EXISTS(DbBasketItem.TABLE_NAME).execute(db);
    createTable(db);
  }

  public Observable<List<DbBasketItem>> getBasket() {
    return query(
        SELECT(
            DbBasketItem.COL_MENU_ITEM_ID,
            DbBasketItem.COL_AMOUNT)
            .FROM(DbBasketItem.TABLE_NAME))
        .run()
        .mapToList(DbBasketItemMapper.MAPPER);
  }

  public Completable insertBasketItem(String menuItemId, int amount) {
    ContentValues values = DbBasketItemMapper.contentValues()
        .menuItemId(menuItemId)
        .amount(amount)
        .build();
    return insert(DbBasketItem.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE).toCompletable();
  }

  public Completable deleteBasketItem(String menuItemId) {
    return delete(DbBasketItem.TABLE_NAME, DbBasketItem.COL_MENU_ITEM_ID + "= ?", menuItemId).toCompletable();
  }
}
