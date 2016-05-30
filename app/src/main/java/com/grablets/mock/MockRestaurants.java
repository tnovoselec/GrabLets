package com.grablets.mock;

import com.grablets.viewmodel.RestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.grablets.viewmodel.RestaurantsViewModel.*;

public class MockRestaurants {

  public static RestaurantsViewModel mock() {
    List<RestaurantsViewModel.RestaurantViewModel> restaurantViewModels = new ArrayList<>();

    RestaurantViewModel restaurantViewModel = new RestaurantViewModel(
        "0",
        "Kod dede",
        "Pečenjarnica u Prečkom poznata po dobrim ćevapima",
        "https://media-cdn.tripadvisor.com/media/photo-s/08/b5/40/d7/photo0jpg.jpg", true);
    restaurantViewModels.add(restaurantViewModel);

    restaurantViewModel = new RestaurantViewModel(
        "1",
        "Karaka",
        "Piratski restoran u srcu Zagreba, poznat po odličnim fajitama",
        "http://www.idemvan.hr/content_images/big24805647zfgmytvbwr.jpg", false);
    restaurantViewModels.add(restaurantViewModel);

    restaurantViewModel = new RestaurantViewModel(
        "2",
        "Takenoko",
        "Najbolji sushi restoran u Zagrebu",
        "http://i1.stay.com/i/GPIGV_CqqlizKefVYfpef7Di604=/C960x640/venue/570/69/7172991.jpg", false);
    restaurantViewModels.add(restaurantViewModel);

    return new RestaurantsViewModel(restaurantViewModels);
  }
}
