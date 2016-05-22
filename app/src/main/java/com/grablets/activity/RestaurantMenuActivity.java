package com.grablets.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.grablets.R;
import com.grablets.Router;
import com.grablets.business.BasketManager;
import com.grablets.di.ActivityComponent;
import com.grablets.fragment.RestaurantMenuFragment;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class RestaurantMenuActivity extends BaseActivity {

  public static final String KEY_RESTAURANT_ID = "restaurant_id";
  public static final String KEY_RESTAURANT_NAME = "restaurant_name";

  @BindView(R.id.basket_label)
  TextView basketLabel;
  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @Inject
  BasketManager basketManager;

  @Inject
  Router router;

  private Subscriber<Map<String, Integer>> basketSubscriber;

  public static Intent createIntent(Context context, String restaurantId, String restaurantName) {
    Intent intent = new Intent(context, RestaurantMenuActivity.class);
    intent.putExtra(KEY_RESTAURANT_ID, restaurantId);
    intent.putExtra(KEY_RESTAURANT_NAME, restaurantName);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant_menu);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(true);

    if (savedInstanceState == null) {
      String restaurantId = getIntent().getStringExtra(KEY_RESTAURANT_ID);
      String restaurantName = getIntent().getStringExtra(KEY_RESTAURANT_NAME);
      RestaurantMenuFragment restaurantMenuFragment = RestaurantMenuFragment.create(restaurantId, restaurantName);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.restaurant_menu_container, restaurantMenuFragment, RestaurantMenuFragment.TAG)
          .commit();
      getSupportActionBar().setTitle(restaurantName);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    basketSubscriber = createBasketSubscriber();
    basketManager.getRelay().subscribe(basketSubscriber);
  }

  @Override
  protected void onPause() {
    super.onPause();
    basketSubscriber.unsubscribe();
  }

  @Override
  protected void inject(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @OnClick(R.id.basket_label)
  public void onBasketClicked() {
    router.showCheckoutActivity();
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

  private Subscriber<Map<String, Integer>> createBasketSubscriber() {
    return new Subscriber<Map<String, Integer>>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onNext(Map<String, Integer> basketItems) {
        int itemsCount = 0;
        for (int count : basketItems.values()) {
          itemsCount += count;
        }
        basketLabel.setText(getResources().getQuantityString(R.plurals.basket_item_number, itemsCount, itemsCount));
      }
    };
  }
}
