package com.grablets.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grablets.R;
import com.grablets.adapter.RestaurantsAdapter;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.RestaurantsMvp;
import com.grablets.mvp.presenter.RestaurantsPresenter;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class RestaurantsFragment extends BaseFragment implements RestaurantsMvp.View {

  public static final String TAG = RestaurantsFragment.class.getSimpleName();

  @BindView(R.id.restaurants_recycler_view)
  RecyclerView restaurantsRecyclerView;

  @Inject
  RestaurantsPresenter restaurantsPresenter;

  RestaurantsAdapter restaurantsAdapter;

  public static RestaurantsFragment create() {
    return new RestaurantsFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_restaurants, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    restaurantsPresenter.activate();
    restaurantsPresenter.attachView(this);
    restaurantsPresenter.getRestaurants();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @Override
  public void renderRestaurants(RestaurantsViewModel restaurantsViewModel) {
    restaurantsAdapter = new RestaurantsAdapter(restaurantsViewModel.restaurantViewModels);
    restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    restaurantsRecyclerView.setAdapter(restaurantsAdapter);
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }

}
