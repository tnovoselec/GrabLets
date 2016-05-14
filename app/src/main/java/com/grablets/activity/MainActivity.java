package com.grablets.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.grablets.R;
import com.grablets.business.PreferenceAccessor;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.DailyMenuFragment;
import com.grablets.internal.GrabLetsMenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;
  @BindView(R.id.nav_view)
  NavigationView navigationView;

  @Inject
  PreferenceAccessor preferenceAccessor;

  private DailyMenuFragment dailyMenuFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(this);

    if (savedInstanceState == null) {
      dailyMenuFragment = DailyMenuFragment.create();
      getSupportFragmentManager().beginTransaction().replace(R.id.main_container, dailyMenuFragment).commit();
    }
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_daily_menu) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.DAILY_MENU.itemId);
    } else if (id == R.id.nav_restaurants) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.RESTAURANT_LIST.itemId);
    } else if (id == R.id.nav_my_orders) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.MY_ORDERS.itemId);
    } else if (id == R.id.nav_my_restaurants) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.MY_RESTAURANTS.itemId);
    } else if (id == R.id.nav_news) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.NEWS.itemId);
    } else if (id == R.id.nav_settings) {
      preferenceAccessor.setActiveMenuItem(GrabLetsMenuItem.SETTINGS.itemId);
    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}