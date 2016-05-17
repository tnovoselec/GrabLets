package com.grablets.receiver;

import android.content.Context;
import android.content.Intent;

import com.grablets.GrabLetsApplication;
import com.grablets.business.NotificationScheduler;

import javax.inject.Inject;

public class BootBroadcastReceiver extends BaseBroadcastReceiver {

  private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

  @Inject
  NotificationScheduler notificationScheduler;

  @Override
  public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);
    if (intent.getAction().equals(ACTION_BOOT)) {
      notificationScheduler.scheduleNotificationAlarm();
    }
  }

  @Override
  protected void daggerInject(Context context) {
    ((GrabLetsApplication) context.getApplicationContext()).getComponent().inject(this);
  }
}


