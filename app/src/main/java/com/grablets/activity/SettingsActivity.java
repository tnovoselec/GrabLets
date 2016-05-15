package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private SettingsFragment settingsFragment;

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

    if (savedInstanceState == null){
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
}
