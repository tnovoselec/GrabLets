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
import com.grablets.mvp.MyRestaurantsMvp;
import com.grablets.mvp.presenter.MyRestaurantsPresenter;
import com.grablets.viewmodel.RestaurantsViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class MyRestaurantsFragment extends BaseFragment implements MyRestaurantsMvp.View, RestaurantsAdapter.RestaurantClickListener {

  public static final String TAG = MyRestaurantsFragment.class.getSimpleName();

  @BindView(R.id.restaurants_recycler_view)
  RecyclerView restaurantsRecyclerView;
  @BindView(R.id.restaurants_progress)
  View restaurantsProgress;

  @Inject
  MyRestaurantsPresenter restaurantsPresenter;

  RestaurantsAdapter restaurantsAdapter;

  public static MyRestaurantsFragment create() {
    return new MyRestaurantsFragment();
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
    restaurantsProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }


  @Override
  public void renderRestaurants(RestaurantsViewModel restaurantsViewModel) {
    restaurantsAdapter = new RestaurantsAdapter(restaurantsViewModel.restaurantViewModels, this);
    restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    restaurantsRecyclerView.setAdapter(restaurantsAdapter);
    restaurantsProgress.setVisibility(View.GONE);
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }

  @Override
  public void onRestaurantClicked(RestaurantsViewModel.RestaurantViewModel restaurantViewModel) {
    restaurantsPresenter.onRestaurantClicked(restaurantViewModel.id, restaurantViewModel.title);
  }

  @Override
  public void onRestaurantFavouriteClicked(RestaurantsViewModel.RestaurantViewModel restaurantViewModel, boolean isFavourite) {

  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }
}
