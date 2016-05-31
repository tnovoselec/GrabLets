package com.grablets.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grablets.R;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.ui.utils.SimpleTextWatcher;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.philio.pinentry.PinEntryView;

public class SettingsPinFragment extends BaseFragment {

  public static final String TAG = SettingsPinFragment.class.getSimpleName();

  public interface SettingsPinListener {
    void onPinConfirmed();

    void onPinRemoved();
  }

  @BindView(R.id.settings_ping_enter_container)
  View settingsPinEnterContainer;

  @BindView(R.id.settings_ping_confirm_container)
  View settingsPinConfirmContainer;

  @BindView(R.id.settings_pin_enter)
  PinEntryView settingsPinEnter;

  @BindView(R.id.settings_pin_confirm)
  PinEntryView settingsPinConfirm;

  @BindView(R.id.settings_pin_save)
  Button settingsPinSave;

  @Inject
  PreferenceAccessor preferenceAccessor;

  private SettingsPinListener settingsPinListener;

  public static SettingsPinFragment create() {
    return new SettingsPinFragment();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_settings_pin, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    settingsPinEnter.addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        if (s.length() == 4) {
          settingsPinConfirm.requestFocus();
        }
      }
    });

    settingsPinConfirm.addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        if (s.length() == 4) {
          validatePins();
        }
      }
    });
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof SettingsPinListener) {
      settingsPinListener = (SettingsPinListener) context;
    }
  }

  @OnClick(R.id.settings_pin_save)
  public void onSavePinClicked() {
    String enteredPin = settingsPinEnter.getText().toString();
    preferenceAccessor.setSecurityPin(enteredPin);
    if (settingsPinListener != null) {
      settingsPinListener.onPinConfirmed();
    }

    settingsPinConfirm.clearText();
    settingsPinEnter.clearText();
  }

  @OnClick(R.id.settings_pin_remove)
  public void onRemovePinClicked() {
    preferenceAccessor.setSecurityPin(null);
    if (settingsPinListener != null) {
      settingsPinListener.onPinRemoved();
    }
    settingsPinConfirm.clearText();
    settingsPinEnter.clearText();
  }

  private void validatePins() {
    String enteredPin = settingsPinEnter.getText().toString();
    String confirmedPin = settingsPinConfirm.getText().toString();

    settingsPinSave.setEnabled(enteredPin.equals(confirmedPin));
  }

}
