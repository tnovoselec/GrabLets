package com.grablets.db.model;

import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

@ObjectMappable
public class DbRestaurantMenuItem {

  public static final String TABLE_NAME = "RestaurantMenuItem";
  public static final String COL_ID = "id";
  public static final String COL_RESTAURANT_ID = "restaurantId";
  public static final String COL_TITLE = "title";
  public static final String COL_DESCRIPTION = "description";
  public static final String COL_IMAGE_URL = "imageUrl";

  @Column(COL_ID)
  private String id;
  @Column(COL_RESTAURANT_ID)
  private String restaurantId;
  @Column(COL_TITLE)
  private String title;
  @Column(COL_DESCRIPTION)
  private String description;
  @Column(COL_IMAGE_URL)
  private String imageUrl;

  public DbRestaurantMenuItem() {
  }

  public DbRestaurantMenuItem(String id, String restaurantId,  String title, String description, String imageUrl) {
    this.description = description;
    this.id = id;
    this.imageUrl = imageUrl;
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(String restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
