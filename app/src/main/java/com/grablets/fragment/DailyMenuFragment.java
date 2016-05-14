package com.grablets.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grablets.R;
import com.grablets.adapter.DailyMenuAdapter;
import com.grablets.mvp.DailyMenuMvp;
import com.grablets.mvp.presenter.DailyMenuPresenter;
import com.grablets.viewmodel.DailyMenuViewModel;

import butterknife.BindView;

public class DailyMenuFragment extends BaseFragment implements DailyMenuMvp.View {

  @BindView(R.id.daily_menu_recycler_view)
  RecyclerView dailyMenuRecyclerView;

  DailyMenuAdapter dailyMenuAdapter;

  DailyMenuPresenter dailyMenuPresenter;

  public static DailyMenuFragment create(){
    return new DailyMenuFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dailyMenuPresenter = new DailyMenuPresenter();
  }

  @Override
  public void onResume() {
    super.onResume();
    dailyMenuPresenter.activate();
    dailyMenuPresenter.attachView(this);
    dailyMenuPresenter.getDailyMenu();
  }

  @Override
  public void onPause() {
    super.onPause();
    dailyMenuPresenter.deactivate();
    dailyMenuPresenter.detachView();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_daily_menu, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    dailyMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  @Override
  public void renderDailyMenu(DailyMenuViewModel dailyMenuViewModel) {
    dailyMenuAdapter = new DailyMenuAdapter(dailyMenuViewModel.menuItemViewModels);
    dailyMenuRecyclerView.setAdapter(dailyMenuAdapter);
  }

  @Override
  public void showErrorMessage(int errorMessageId) {

  }
}
