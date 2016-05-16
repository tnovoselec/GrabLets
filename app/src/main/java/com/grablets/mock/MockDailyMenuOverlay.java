package com.grablets.mock;

import com.grablets.viewmodel.DailyMenuOverlayViewModel;
import com.grablets.viewmodel.DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel;
import com.grablets.viewmodel.DailyMenuOverlayViewModel.RestaurantOverlayViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDailyMenuOverlay {

  public static DailyMenuOverlayViewModel getDailyModel() {
    Map<RestaurantOverlayViewModel, List<DailyMenuItemOverlayViewModel>> data = new HashMap<>();

    RestaurantOverlayViewModel restaurantOverlayViewModel = new RestaurantOverlayViewModel("Kod dede");
    List<DailyMenuItemOverlayViewModel> dailyMenuOverlayViewModels = new ArrayList<>();

    DailyMenuItemOverlayViewModel dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Ćevapi s lukom",
        "Fini ćevapi s lukom",
        "http://www.index.hr/images2/cevapi_ilustracija_625.jpeg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Čobanac",
        "Originalni slavonski čobanac sa veprovinom i ostalom divljači",
        "http://www.volim-meso.hr/wordpress/wp-content/uploads/2014/11/cobanac-naslovna.jpg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Odojak",
        "Ohlađeni pečeni odojak",
        "http://cdn.coolinarika.net/image/peceni-odojak-4724e15093c8f8c5b3161a79fa4bd144_header.jpg?v=5",
        50.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    data.put(restaurantOverlayViewModel, dailyMenuOverlayViewModels);

    restaurantOverlayViewModel = new RestaurantOverlayViewModel("Grana");
    dailyMenuOverlayViewModels = new ArrayList<>();

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Ćevapi s lukom",
        "Fini ćevapi s lukom",
        "http://www.index.hr/images2/cevapi_ilustracija_625.jpeg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Čobanac",
        "Originalni slavonski čobanac sa veprovinom i ostalom divljači",
        "http://www.volim-meso.hr/wordpress/wp-content/uploads/2014/11/cobanac-naslovna.jpg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Odojak",
        "Ohlađeni pečeni odojak",
        "http://cdn.coolinarika.net/image/peceni-odojak-4724e15093c8f8c5b3161a79fa4bd144_header.jpg?v=5",
        50.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    data.put(restaurantOverlayViewModel, dailyMenuOverlayViewModels);

    restaurantOverlayViewModel = new RestaurantOverlayViewModel("Pri Zvoncu");
    dailyMenuOverlayViewModels = new ArrayList<>();

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Ćevapi s lukom",
        "Fini ćevapi s lukom",
        "http://www.index.hr/images2/cevapi_ilustracija_625.jpeg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Čobanac",
        "Originalni slavonski čobanac sa veprovinom i ostalom divljači",
        "http://www.volim-meso.hr/wordpress/wp-content/uploads/2014/11/cobanac-naslovna.jpg",
        30.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    dailyMenuOverlayViewModel = new DailyMenuItemOverlayViewModel(
        "Odojak",
        "Ohlađeni pečeni odojak",
        "http://cdn.coolinarika.net/image/peceni-odojak-4724e15093c8f8c5b3161a79fa4bd144_header.jpg?v=5",
        50.00
    );
    dailyMenuOverlayViewModels.add(dailyMenuOverlayViewModel);

    data.put(restaurantOverlayViewModel, dailyMenuOverlayViewModels);

    return new DailyMenuOverlayViewModel(data);
  }
}
