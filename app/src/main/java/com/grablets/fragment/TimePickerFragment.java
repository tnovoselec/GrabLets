package com.grablets.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

  public static final String TAG = TimePickerFragment.class.getSimpleName();

  private TimePickerDialog.OnTimeSetListener listener;

  public static TimePickerFragment create(){
    return new TimePickerFragment();
  }


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
