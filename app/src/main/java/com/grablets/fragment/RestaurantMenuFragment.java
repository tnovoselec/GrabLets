package com.grablets.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grablets.R;
import com.grablets.activity.RestaurantMenuActivity;
import com.grablets.adapter.DailyMenuAdapter;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.RestaurantMenuMvp;
import com.grablets.mvp.presenter.RestaurantMenuPresenter;
import com.grablets.viewmodel.RestaurantMenuViewModel;

import javax.inject.Inject;

import butterknife.BindView;


public class RestaurantMenuFragment extends BaseFragment implements RestaurantMenuMvp.View, DailyMenuAdapter.MenuItemAmountListener {

  public static final String TAG = RestaurantMenuFragment.class.getSimpleName();


  @BindView(R.id.restaurant_menu_recycler_view)
  RecyclerView dailyMenuRecyclerView;

  DailyMenuAdapter dailyMenuAdapter;

  @Inject
  RestaurantMenuPresenter restaurantMenuPresenter;

  private String restaurantId;


  public static RestaurantMenuFragment create(String restaurantId, String restaurantName) {
    Bundle arguments = new Bundle();
    arguments.putString(RestaurantMenuActivity.KEY_RESTAURANT_ID, restaurantId);
    arguments.putString(RestaurantMenuActivity.KEY_RESTAURANT_NAME, restaurantName);
    RestaurantMenuFragment restaurantMenuFragment = new RestaurantMenuFragment();
    restaurantMenuFragment.setArguments(arguments);
    return restaurantMenuFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    restaurantId = getArguments().getString(RestaurantMenuActivity.KEY_RESTAURANT_ID);
  }

  @Override
  public void onResume() {
    super.onResume();
    restaurantMenuPresenter.activate();
    restaurantMenuPresenter.attachView(this);
    restaurantMenuPresenter.getRestaurantMenu(restaurantId);
  }

  @Override
  public void onPause() {
    super.onPause();
    restaurantMenuPresenter.deactivate();
    restaurantMenuPresenter.detachView();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_restaurant_menu, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    dailyMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @Override
  public void renderDailyMenu(RestaurantMenuViewModel restaurantMenuViewModel) {
    dailyMenuAdapter = new DailyMenuAdapter(restaurantMenuViewModel.menuItemViewModels, this, restaurantMenuViewModel.basketEntries);
    dailyMenuRecyclerView.setAdapter(dailyMenuAdapter);
  }

  @Override
  public void showErrorMessage(@StringRes int errorMessageId) {

  }

  @Override
  public void onAmountChanged(String menuItemId, int newAmount) {
    restaurantMenuPresenter.onMenuItemAmountChanged(menuItemId, newAmount);
  }
}
