package com.grablets.viewmodel;

import java.util.List;
import java.util.Map;

public class DailyMenuOverlayViewModel {

  public final Map<RestaurantOverlayViewModel, List<DailyMenuItemOverlayViewModel>> dailyMenuOverlayData;

  public DailyMenuOverlayViewModel(Map<RestaurantOverlayViewModel, List<DailyMenuItemOverlayViewModel>> dailyMenuOverlayData) {
    this.dailyMenuOverlayData = dailyMenuOverlayData;
  }

  public static class RestaurantOverlayViewModel {
    public final String title;

    public RestaurantOverlayViewModel(String title) {
      this.title = title;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      RestaurantOverlayViewModel that = (RestaurantOverlayViewModel) o;

      return title.equals(that.title);

    }

    @Override
    public int hashCode() {
      return title.hashCode();
    }
  }

  public static class DailyMenuItemOverlayViewModel {
    public final String id;
    public final String title;
    public final String imageUrl;
    public final String description;
    public final double price;

    public DailyMenuItemOverlayViewModel(String id, String title, String description, String imageUrl, double price) {
      this.id = id;
      this.description = description;
      this.title = title;
      this.imageUrl = imageUrl;
      this.price = price;
    }
  }
}
