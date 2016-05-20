package com.grablets.di;


import android.app.Application;
import android.content.Context;

import com.grablets.GrabLetsApplication;
import com.grablets.api.GrabLetsClient;
import com.grablets.business.BasketManager;
import com.grablets.business.NotificationScheduler;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.module.ApplicationModule;
import com.grablets.di.module.DatabaseModule;
import com.grablets.di.module.GrabLetsApiModule;
import com.grablets.di.module.RepositoryModule;
import com.grablets.di.module.UseCaseModule;
import com.grablets.di.qualifier.ForApplication;
import com.grablets.interactor.ClearBasketUseCase;
import com.grablets.interactor.CreateOrderUseCase;
import com.grablets.interactor.GetBasketUseCase;
import com.grablets.interactor.GetRestaurantMenuItemsUseCase;
import com.grablets.interactor.GetRestaurantsUseCase;
import com.grablets.receiver.AlarmReceiver;
import com.grablets.receiver.BootBroadcastReceiver;
import com.grablets.repository.BasketDbRepository;
import com.grablets.repository.RestaurantDbMenuItemsRepository;
import com.grablets.repository.RestaurantsDbRepository;
import com.grablets.service.DailyMenuOverlayService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        ApplicationModule.class,
        GrabLetsApiModule.class,
        DatabaseModule.class,
        RepositoryModule.class,
        UseCaseModule.class
    }
)
public interface ApplicationComponent {

  final class Initializer {
    static public ApplicationComponent init(GrabLetsApplication application) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .databaseModule(new DatabaseModule(application))
          .build();
    }

    // No instances
    private Initializer() {
    }
  }

  @ForApplication
  Context provideApplicationContext();

  Application getApplication();

  PreferenceAccessor getPreferenceAccessor();

  NotificationScheduler getNotificationScheduler();

  BasketManager getBasketManager();

  GrabLetsClient getGrabLetsClient();

  RestaurantsDbRepository getRestaurantsRepository();

  RestaurantDbMenuItemsRepository getRestaurantMenuItemsRepository();

  BasketDbRepository getBasketDbRepository();

  GetRestaurantsUseCase getRestaurantsUseCase();

  GetBasketUseCase getBasketUseCase();

  GetRestaurantMenuItemsUseCase getRestaurantMenuItemsUseCase();

  CreateOrderUseCase getCreateOrderUseCase();

  ClearBasketUseCase getClearBasketUseCase();

  void inject(GrabLetsApplication commerceApplication);

  void inject(BootBroadcastReceiver bootBroadcastReceiver);

  void inject(AlarmReceiver alarmReceiver);

  void inject(DailyMenuOverlayService dailyMenuOverlayService);
}
