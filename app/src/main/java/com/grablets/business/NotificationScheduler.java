package com.grablets.business;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.grablets.receiver.AlarmReceiver;
import com.grablets.receiver.BootBroadcastReceiver;

import java.util.Calendar;

import javax.inject.Inject;

public class NotificationScheduler {

  private final Context context;
  private final PreferenceAccessor preferenceAccessor;

  private PendingIntent alarmIntent;

  @Inject
  public NotificationScheduler(Context context, PreferenceAccessor preferenceAccessor) {
    this.context = context;
    this.preferenceAccessor = preferenceAccessor;
  }

  public void scheduleNotificationAlarm() {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmReceiver.class);
    alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    long notificationsTime = preferenceAccessor.getNotificationsTime();
    if (notificationsTime < System.currentTimeMillis()) {
      return;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(notificationsTime);

//    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//        calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    ComponentName receiver = new ComponentName(context, BootBroadcastReceiver.class);
    PackageManager pm = context.getPackageManager();

    pm.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP);
  }


  public void cancelAlarm(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    if (alarmManager != null) {
      alarmManager.cancel(alarmIntent);
    }

    ComponentName receiver = new ComponentName(context, BootBroadcastReceiver.class);
    PackageManager pm = context.getPackageManager();

    pm.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP);
  }
}
