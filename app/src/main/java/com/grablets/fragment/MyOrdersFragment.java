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
import com.grablets.adapter.MyOrdersAdapter;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.MyOrdersMvp;
import com.grablets.mvp.presenter.MyOrdersPresenter;
import com.grablets.viewmodel.MyOrdersViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class MyOrdersFragment extends BaseFragment implements MyOrdersMvp.View {

  public static final String TAG = MyOrdersFragment.class.getSimpleName();

  @BindView(R.id.my_orders_recycler_view)
  RecyclerView myOrdersRecyclerView;

  @Inject
  MyOrdersPresenter myOrdersPresenter;

  public static MyOrdersFragment create() {
    return new MyOrdersFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_my_orders, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    myOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  @Override
  public void onResume() {
    super.onResume();
    myOrdersPresenter.activate();
    myOrdersPresenter.attachView(this);
    myOrdersPresenter.getOrders();
  }

  @Override
  public void onPause() {
    super.onPause();
    myOrdersPresenter.detachView();
    myOrdersPresenter.deactivate();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @Override
  public void renderOrders(MyOrdersViewModel myOrdersViewModel) {
    MyOrdersAdapter myOrdersAdapter = new MyOrdersAdapter(myOrdersViewModel.orderItemViewModels, LayoutInflater.from(getContext()));
    myOrdersRecyclerView.setAdapter(myOrdersAdapter);
  }

  @Override
  public void showErrorMessage(@StringRes int errorMessageId) {

  }
}
