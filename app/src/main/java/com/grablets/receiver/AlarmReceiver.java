package com.grablets.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.grablets.GrabLetsApplication;
import com.grablets.business.NotificationScheduler;
import com.grablets.service.DailyMenuService;

import javax.inject.Inject;

public class AlarmReceiver extends WakefulBroadcastReceiver {

  @Inject
  NotificationScheduler notificationScheduler;

  @Override
  public void onReceive(Context context, Intent intent) {
    ((GrabLetsApplication) context.getApplicationContext()).getComponent().inject(this);

    notificationScheduler.scheduleNotificationAlarm();

    Intent service = new Intent(context, DailyMenuService.class);

    startWakefulService(context, service);
  }


}
