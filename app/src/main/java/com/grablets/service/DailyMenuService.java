package com.grablets.service;

import android.app.IntentService;
import android.content.Intent;

public class DailyMenuService extends IntentService {

  public DailyMenuService() {
    this("DailyMenuService");
  }

  public DailyMenuService(String name) {
    super(name);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    startService(new Intent(this, OverlayService.class));
  }
}
