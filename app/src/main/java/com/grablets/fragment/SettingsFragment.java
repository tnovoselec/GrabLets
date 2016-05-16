package com.grablets.fragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.grablets.R;
import com.grablets.business.NotificationScheduler;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.service.OverlayService;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnTouch;

public class SettingsFragment extends BaseFragment {

  public static final String TAG = SettingsFragment.class.getSimpleName();

  @BindView(R.id.settings_daily_menu_notification)
  CheckBox settingsDailyMenuNotification;
  @BindView(R.id.settings_daily_menu_notification_time)
  TextView settingsDailyMenuNotificationTime;
  @BindView(R.id.settings_first_name_label)
  TextView settingsFirstNameLabel;
  @BindView(R.id.settings_last_name_label)
  TextView settingsLastNameLabel;
  @BindView(R.id.settings_phone_label)
  EditText settingsPhoneLabel;
  @BindView(R.id.settings_address_label)
  EditText settingsAddressLabel;
  @BindView(R.id.settings_delivery_time_label)
  EditText settingsDeliveryTimeLabel;
  @BindView(R.id.settings_additional_info_label)
  EditText settingsAdditionalInfoLabel;

  @Inject
  PreferenceAccessor preferenceAccessor;

  @Inject
  NotificationScheduler notificationScheduler;

  public static SettingsFragment create() {
    return new SettingsFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_settings, container, false);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.settings, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_settings){
      getActivity().startService(new Intent(getContext(), OverlayService.class));
      return true;
    }
    return super.onOptionsItemSelected(item);

  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @OnCheckedChanged(R.id.settings_daily_menu_notification)
  public void onDailyMenuNotificationChanged(boolean checked) {
    if (checked) {
      showNotificationTimePicker();
    }
    preferenceAccessor.setNotificationsEnabled(checked);
  }

  @OnTouch(R.id.settings_delivery_time_label)
  public boolean onDeliveryTimeClicked(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      showDeliveryTimePicker();
      return true;
    }
    return false;
  }

  private void showDeliveryTimePicker() {
    TimePickerFragment timePickerFragment = TimePickerFragment.create();
    timePickerFragment.setListener(new DeliveryTimeListener());
    timePickerFragment.show(getChildFragmentManager(), TimePickerFragment.TAG);
  }

  private void showNotificationTimePicker() {
    TimePickerFragment timePickerFragment = TimePickerFragment.create();
    timePickerFragment.setListener(new NotificationTimeListener());
    timePickerFragment.show(getChildFragmentManager(), TimePickerFragment.TAG);
  }

  private void scheduleNotifications(int hourOfDay, int minute) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, 0);
    preferenceAccessor.setNotificationsTime(calendar.getTimeInMillis());
    notificationScheduler.scheduleNotificationAlarm();
  }


  class DeliveryTimeListener implements TimePickerDialog.OnTimeSetListener {
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      settingsDeliveryTimeLabel.setText(String.format("%02d:%02d", hourOfDay, minute));
    }
  }

  class NotificationTimeListener implements TimePickerDialog.OnTimeSetListener {
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      settingsDailyMenuNotificationTime.setText(String.format("%02d:%02d", hourOfDay, minute));

      scheduleNotifications(hourOfDay, minute);

    }
  }

}
