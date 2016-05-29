package com.grablets.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dpizarro.pinview.library.PinView;
import com.grablets.R;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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
  PinView settingsPinEnter;

  @BindView(R.id.settings_pin_confirm)
  PinView settingsPinConfirm;

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
    settingsPinEnter.setOnCompleteListener((completed, pinResults) -> settingsPinConfirm.requestFocus());

    settingsPinConfirm.setOnCompleteListener((completed, pinResults) -> validatePins());
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof SettingsPinListener){
      settingsPinListener = (SettingsPinListener) context;
    }
  }

  @OnClick(R.id.settings_pin_save)
  public void onSavePinClicked(){
    String enteredPin = settingsPinEnter.getPinResults();
    preferenceAccessor.setSecurityPin(enteredPin);
    if (settingsPinListener != null){
      settingsPinListener.onPinConfirmed();
    }

    settingsPinConfirm.clear();
    settingsPinEnter.clear();
  }

  @OnClick(R.id.settings_pin_remove)
  public void onRemovePinClicked(){
    preferenceAccessor.setSecurityPin(null);
    if (settingsPinListener != null){
      settingsPinListener.onPinRemoved();
    }
    settingsPinConfirm.clear();
    settingsPinEnter.clear();
  }

  private void validatePins() {
    String enteredPin = settingsPinEnter.getPinResults();
    String confirmedPin = settingsPinConfirm.getPinResults();

    settingsPinSave.setEnabled(enteredPin.equals(confirmedPin));
  }

}
