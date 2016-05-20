package com.grablets.mock;

import com.grablets.viewmodel.DailyMenuViewModel;
import com.grablets.viewmodel.DailyMenuViewModel.MenuItemViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockDailyMenu {
  public static DailyMenuViewModel getDailyMenu() {
    List<MenuItemViewModel> menuItemViewModels = new ArrayList<>();

    MenuItemViewModel menuItemViewModel = new MenuItemViewModel(
        "", "Ćevapi s lukom",
        "Fini ćevapi s lukom",
        "http://www.index.hr/images2/cevapi_ilustracija_625.jpeg",
        30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel(
        "", "Čobanac",
        "Originalni slavonski čobanac sa veprovinom i ostalom divljači",
        "http://www.volim-meso.hr/wordpress/wp-content/uploads/2014/11/cobanac-naslovna.jpg",
        30.00);
    menuItemViewModels.add(menuItemViewModel);

    menuItemViewModel = new MenuItemViewModel(
        "", "Odojak",
        "Ohlađeni pečeni odojak",
        "http://cdn.coolinarika.net/image/peceni-odojak-4724e15093c8f8c5b3161a79fa4bd144_header.jpg?v=5",
        50.00);
    menuItemViewModels.add(menuItemViewModel);


    DailyMenuViewModel dailyMenuViewModel = new DailyMenuViewModel(menuItemViewModels, new HashMap<>());
    return dailyMenuViewModel;
  }
}
