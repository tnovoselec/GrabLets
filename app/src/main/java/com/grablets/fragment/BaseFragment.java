package com.grablets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject((ComponentProvider<ActivityComponent>) getActivity());
  }

  protected abstract void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider);


  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }
}
