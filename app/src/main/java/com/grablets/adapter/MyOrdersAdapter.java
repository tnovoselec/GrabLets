package com.grablets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grablets.R;
import com.grablets.utils.DateUtils;
import com.grablets.utils.FormatUtils;
import com.grablets.viewmodel.MyOrdersViewModel.MenuItemViewModel;
import com.grablets.viewmodel.MyOrdersViewModel.OrderItemViewModel;
import com.grablets.viewmodel.MyOrdersViewModel.RestaurantViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyOrdersViewHolder> {

  private final List<OrderItemViewModel> orderItemViewModels;
  private final LayoutInflater layoutInflater;

  public MyOrdersAdapter(List<OrderItemViewModel> orderItemViewModels, LayoutInflater layoutInflater) {
    this.orderItemViewModels = orderItemViewModels;
    this.layoutInflater = layoutInflater;
  }

  @Override
  public MyOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.item_my_orders, parent, false);
    return new MyOrdersViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyOrdersViewHolder holder, int position) {
    holder.fillView(orderItemViewModels.get(position));
  }

  @Override
  public int getItemCount() {
    return orderItemViewModels.size();
  }

  class MyOrdersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_my_orders_date)
    TextView myOrdersDate;
    @BindView(R.id.item_my_orders_total)
    TextView myOrdersTotal;
    @BindView(R.id.item_my_orders_restaurants_container)
    LinearLayout myOrdersRestaurantsContainer;


    public MyOrdersViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(OrderItemViewModel orderItemViewModel) {
      myOrdersDate.setText(DateUtils.formatMyOrderDate(orderItemViewModel.orderTimestamp));
      myOrdersTotal.setText(FormatUtils.formatPrice(orderItemViewModel.total));
      fillRestaurants(orderItemViewModel.restaurantViewModels);
    }

    void fillRestaurants(List<RestaurantViewModel> restaurantViewModels) {
      myOrdersRestaurantsContainer.removeAllViews();

      for (RestaurantViewModel restaurantViewModel : restaurantViewModels) {
        View restaurantView = layoutInflater.inflate(R.layout.item_my_orders_restaurant, myOrdersRestaurantsContainer, false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(restaurantView);
        restaurantViewHolder.fillView(restaurantViewModel);
        myOrdersRestaurantsContainer.addView(restaurantView);
      }
    }
  }

  class RestaurantViewHolder {

    @BindView(R.id.item_my_orders_restaurant_title)
    TextView myOrdersRestaurantTitle;

    @BindView(R.id.item_my_orders_restaurant_menu_items_container)
    LinearLayout myOrdersRestaurantMenuItemsContainer;

    RestaurantViewHolder(View itemView) {
      ButterKnife.bind(this, itemView);
    }

    void fillView(RestaurantViewModel restaurantViewModel) {
      myOrdersRestaurantTitle.setText(restaurantViewModel.title);
      fillMenuItems(restaurantViewModel.menuItemViewModels);
    }

    private void fillMenuItems(List<MenuItemViewModel> menuItemViewModels) {
      myOrdersRestaurantMenuItemsContainer.removeAllViews();

      for (MenuItemViewModel menuItemViewModel : menuItemViewModels) {
        View menuItemView = layoutInflater.inflate(R.layout.item_my_orders_menu_item, myOrdersRestaurantMenuItemsContainer, false);
        MenuItemViewHolder menuItemViewHolder = new MenuItemViewHolder(menuItemView);
        menuItemViewHolder.fillView(menuItemViewModel);
        myOrdersRestaurantMenuItemsContainer.addView(menuItemView);
      }
    }
  }

  class MenuItemViewHolder{

    @BindView(R.id.item_my_orders_menu_item_title)
    TextView myOrdersMenuItemTitle;
    @BindView(R.id.item_my_orders_menu_item_total)
    TextView myOrdersMenuItemTotal;

    public MenuItemViewHolder(View itemView) {
      ButterKnife.bind(this, itemView);
    }

    void fillView(MenuItemViewModel menuItemViewModel){
      myOrdersMenuItemTitle.setText(menuItemViewModel.quantity + " X " + menuItemViewModel.title);
      myOrdersMenuItemTotal.setText(FormatUtils.formatPrice(menuItemViewModel.quantity * menuItemViewModel.price));
    }
  }


}
