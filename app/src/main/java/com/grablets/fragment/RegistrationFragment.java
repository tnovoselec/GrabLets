package com.grablets.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.RegistrationMvp;
import com.grablets.mvp.presenter.RegistrationPresenter;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

public class RegistrationFragment extends BaseFragment implements RegistrationMvp.View, TimePickerDialog.OnTimeSetListener {

  public static final String TAG = RegistrationFragment.class.getSimpleName();

  @BindView(R.id.user_first_name_label)
  EditText userFirstNameLabel;
  @BindView(R.id.user_last_name_label)
  EditText userLastNameLabel;
  @BindView(R.id.user_phone_label)
  EditText userPhoneLabel;
  @BindView(R.id.user_address_label)
  EditText userAddressLabel;
  @BindView(R.id.user_additional_info_label)
  EditText userAdditionalInfoLabel;
  @BindView(R.id.user_delivery_time_label)
  EditText userDeliveryTimeLabel;

  @Inject
  RegistrationPresenter registrationPresenter;

  public static RegistrationFragment create() {
    return new RegistrationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_registration, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    registrationPresenter.activate();
    registrationPresenter.attachView(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    registrationPresenter.deactivate();
    registrationPresenter.detachView();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @OnClick(R.id.user_register)
  public void onRegisterClicked() {
    registrationPresenter.register(
        userFirstNameLabel.getText().toString(),
        userLastNameLabel.getText().toString(),
        userPhoneLabel.getText().toString(),
        userAddressLabel.getText().toString(),
        userAdditionalInfoLabel.getText().toString()
    );
  }

  @OnTouch(R.id.user_delivery_time_label)
  public boolean onDeliveryTimeClicked(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      TimePickerFragment timePickerFragment = new TimePickerFragment();
      timePickerFragment.setListener(this);
      timePickerFragment.show(getChildFragmentManager(), "timePicker");
      return true;
    }
    return false;
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    userDeliveryTimeLabel.setText(String.format("%02d:%02d", hourOfDay, minute));
  }

  public static class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the current time as the default values for the picker
      final Calendar c = Calendar.getInstance();
      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);

      // Create a new instance of TimePickerDialog and return it
      return new TimePickerDialog(getActivity(), listener, hour, minute,
          DateFormat.is24HourFormat(getActivity()));
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
      this.listener = listener;
    }
  }
}
