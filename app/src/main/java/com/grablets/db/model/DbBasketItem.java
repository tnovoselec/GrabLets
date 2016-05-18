package com.grablets.db.model;

import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

@ObjectMappable
public class DbBasketItem {

  public static final String TABLE_NAME = "Basket";
  public static final String COL_MENU_ITEM_ID = "menuItemId";
  public static final String COL_AMOUNT = "amount";

  @Column(COL_MENU_ITEM_ID)
  private String menuItemId;
  @Column(COL_AMOUNT)
  private int amount;

  public DbBasketItem() {
  }

  public DbBasketItem(String menuItemId, int amount) {
    this.menuItemId = menuItemId;
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getMenuItemId() {
    return menuItemId;
  }

  public void setMenuItemId(String menuItemId) {
    this.menuItemId = menuItemId;
  }
}
