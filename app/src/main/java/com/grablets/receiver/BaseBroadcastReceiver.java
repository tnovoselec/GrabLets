package com.grablets.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    daggerInject(context);
  }

  protected abstract void daggerInject(Context context);
}
