package com.grablets.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grablets.R;
import com.grablets.di.ActivityComponent;
import com.grablets.di.ComponentProvider;
import com.grablets.mvp.CheckoutMvp;
import com.grablets.mvp.presenter.CheckoutPresenter;
import com.grablets.viewmodel.CheckoutViewModel;
import com.grablets.viewmodel.CheckoutViewModel.CheckoutMenuItemViewModel;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckoutFragment extends BaseFragment implements CheckoutMvp.View {

  public static final String TAG = CheckoutFragment.class.getSimpleName();

  @BindView(R.id.checkout_menu_container)
  ViewGroup checkoutMenuContainer;
  @BindView(R.id.checkout_delivery_address)
  EditText checkoutDeliveryAddress;
  @BindView(R.id.checkout_delivery_time)
  EditText checkoutDeliveryTime;

  @Inject
  CheckoutPresenter checkoutPresenter;

  public static CheckoutFragment create() {
    return new CheckoutFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_checkout, container, false);
  }

  @Override
  public void onResume() {
    super.onResume();
    checkoutPresenter.activate();
    checkoutPresenter.attachView(this);
    checkoutPresenter.getCheckoutData();
  }

  @Override
  public void onPause() {
    super.onPause();
    checkoutPresenter.deactivate();
    checkoutPresenter.detachView();
  }

  @Override
  protected void inject(ComponentProvider<ActivityComponent> activityComponentComponentProvider) {
    activityComponentComponentProvider.getComponent().inject(this);
  }

  @Override
  public void renderCheckoutData(CheckoutViewModel checkoutViewModel) {
    fillContainer(checkoutViewModel);
    fillUserData(checkoutViewModel);
  }

  @Override
  public void showOrderSuccessfulMessage(@StringRes int messageId) {
    Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showErrorMessage(int errorMessageId) {
    Toast.makeText(getContext(), errorMessageId, Toast.LENGTH_SHORT).show();
  }

  private void fillContainer(CheckoutViewModel checkoutViewModel) {
    checkoutMenuContainer.removeAllViews();
    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
    for (CheckoutMenuItemViewModel checkoutMenuItemViewModel : checkoutViewModel.checkoutMenuItemViewModels) {
      View itemView = layoutInflater.inflate(R.layout.item_checkout_menu_item, checkoutMenuContainer, false);
      TextView title = (TextView) itemView.findViewById(R.id.item_checkout_title);
      title.setText(String.format("%d x %s", checkoutMenuItemViewModel.amount, checkoutMenuItemViewModel.title));
      TextView price = (TextView) itemView.findViewById(R.id.item_checkout_price);
      price.setText(String.valueOf(checkoutMenuItemViewModel.price));
      checkoutMenuContainer.addView(itemView);
    }
  }

  private void fillUserData(CheckoutViewModel checkoutViewModel) {
    checkoutDeliveryAddress.setText(checkoutViewModel.userDataViewModel.deliveryAddress);
    checkoutDeliveryTime.setText(checkoutViewModel.userDataViewModel.deliveryTime);
  }

  @OnClick(R.id.checkout_confirm_order)
  public void onConfirmOrderClicked() {
    String deliveryAddress = checkoutDeliveryAddress.getText().toString();
    Date deliveryTime = Calendar.getInstance().getTime();//checkoutDeliveryTime.getText().toString();
    checkoutPresenter.onConfirmOrderClicked(deliveryAddress, deliveryTime);
  }
}
