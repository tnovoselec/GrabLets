package com.grablets.di.module;

import android.app.Application;

import com.grablets.BuildConfig;
import com.grablets.api.GrabLetsApi;
import com.grablets.api.GrabLetsClient;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Module
public class GrabLetsApiModule {

  private static final String BASE_URL = "http://private-a7425-grablets.apiary-mock.com/";

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
    okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
    return okHttpClient;
  }

  @Provides
  @Singleton
  RestAdapter provideRestAdapter(Application application, OkHttpClient okHttpClient) {
    RestAdapter.Builder builder = new RestAdapter.Builder();
    builder.setClient(new OkClient(okHttpClient))
        .setEndpoint(BASE_URL);

    if (BuildConfig.DEBUG) {
      builder.setLogLevel(RestAdapter.LogLevel.FULL);
    }

    return builder.build();
  }

  @Provides
  @Singleton
  GrabLetsApi provideGrabLetsApi(RestAdapter restAdapter) {
    return restAdapter.create(GrabLetsApi.class);
  }

  @Provides
  GrabLetsClient provideGrabLetsClient(GrabLetsApi grabLetsApi){
    return new GrabLetsClient(grabLetsApi);
  }
}
