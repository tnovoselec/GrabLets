package com.grablets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grablets.R;
import com.grablets.viewmodel.RestaurantsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {

  public interface RestaurantClickListener {
    void onRestaurantClicked(RestaurantsViewModel.RestaurantViewModel restaurantViewModel);
  }

  private final List<RestaurantsViewModel.RestaurantViewModel> restaurantViewModels;
  private final RestaurantClickListener restaurantClickListener;

  public RestaurantsAdapter(List<RestaurantsViewModel.RestaurantViewModel> restaurantViewModels, RestaurantClickListener restaurantClickListener) {
    this.restaurantViewModels = restaurantViewModels;
    this.restaurantClickListener = restaurantClickListener;
  }

  @Override
  public RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
    return new RestaurantsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RestaurantsViewHolder holder, int position) {
    holder.fillView(restaurantViewModels.get(position));
  }

  @Override
  public int getItemCount() {
    return restaurantViewModels.size();
  }

  class RestaurantsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_restaurant_image)
    ImageView itemRestaurantImage;
    @BindView(R.id.item_restaurant_title)
    TextView itemRestaurantTitle;
    @BindView(R.id.item_restaurant_description)
    TextView itemRestaurantDescription;

    public RestaurantsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(RestaurantsViewModel.RestaurantViewModel restaurantViewModel) {
      Glide.with(itemRestaurantImage.getContext())
          .load(restaurantViewModel.imageUrl)
          .centerCrop()
          .into(itemRestaurantImage);
      itemRestaurantTitle.setText(restaurantViewModel.title);
      itemRestaurantDescription.setText(restaurantViewModel.description);
      itemView.setOnClickListener((view) -> restaurantClickListener.onRestaurantClicked(restaurantViewModel));
    }


  }
}
