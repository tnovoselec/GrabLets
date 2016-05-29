package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.SettingsFragment;
import com.grablets.fragment.SettingsPinFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements SettingsFragment.SettingsListener, SettingsPinFragment.SettingsPinListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private SettingsFragment settingsFragment;
  private SettingsPinFragment settingsPinFragment;

  public static Intent createIntent(Context context) {
    return new Intent(context, SettingsActivity.class);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    if (savedInstanceState == null) {
      settingsFragment = SettingsFragment.create();
      getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, settingsFragment, SettingsFragment.TAG).commit();
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void showPinChooser() {
    if (settingsPinFragment == null) {
      settingsPinFragment = SettingsPinFragment.create();
    }
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.anim.slide_in_up, R.anim.do_nothing, R.anim.do_nothing, R.anim.slide_out_down)
        .add(R.id.settings_container, settingsPinFragment, SettingsPinFragment.TAG)
        .addToBackStack(null)
        .commit();
  }

  @Override
  public void onSetupPinClicked() {
    showPinChooser();
  }

  @Override
  public void onPinConfirmed() {
    Toast.makeText(this, R.string.pin_saved, Toast.LENGTH_SHORT).show();
    hidePinChooser();
  }

  @Override
  public void onPinRemoved() {
    Toast.makeText(this, R.string.pin_removed, Toast.LENGTH_SHORT).show();
    hidePinChooser();
  }

  private void hidePinChooser() {
    getSupportFragmentManager().popBackStack();
  }
}
