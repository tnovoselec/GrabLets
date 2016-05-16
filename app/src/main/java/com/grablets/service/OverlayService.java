package com.grablets.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.grablets.R;
import com.grablets.adapter.DailyMenuOverlayAdapter;
import com.grablets.mock.MockDailyMenuOverlay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverlayService extends Service {

  @BindView(R.id.overlay_daily_menu_recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.daily_menu_overlay_container)
  View container;

  private WindowManager windowManager;
  private View overlayView;
  private WindowManager.LayoutParams params;

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    overlayView = LayoutInflater.from(this).inflate(R.layout.layout_overlay, null, false);
    ButterKnife.bind(this, overlayView);


    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(new DailyMenuOverlayAdapter(MockDailyMenuOverlay.getDailyModel().dailyMenuOverlayData));

    params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.TYPE_PHONE,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);

    windowManager.addView(overlayView, params);

    ViewGroup.LayoutParams params = container.getLayoutParams();
    params.height = getScreenHeight() /2;
    container.setLayoutParams(params);

  }

  @OnClick(R.id.overlay_close_button)
  public void onCloseOverlayButtonClicked() {
    removeOverlay();
    stopSelf();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    removeOverlay();
  }

  private void removeOverlay() {
    if (overlayView != null) {
      windowManager.removeView(overlayView);
      overlayView = null;
    }
  }

  private int getScreenHeight() {
    Display display = windowManager.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y;
  }
}
