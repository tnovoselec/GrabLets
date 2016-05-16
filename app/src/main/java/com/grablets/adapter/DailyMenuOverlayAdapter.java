package com.grablets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.bumptech.glide.Glide;
import com.grablets.R;
import com.grablets.viewmodel.DailyMenuOverlayViewModel.DailyMenuItemOverlayViewModel;
import com.grablets.viewmodel.DailyMenuOverlayViewModel.RestaurantOverlayViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyMenuOverlayAdapter extends SectionedRecyclerViewAdapter<DailyMenuOverlayAdapter.OverlayViewHolder> {

  private final Map<RestaurantOverlayViewModel, List<DailyMenuItemOverlayViewModel>> data;
  private final List<RestaurantOverlayViewModel> restaurantOverlayViewModels;

  public DailyMenuOverlayAdapter(Map<RestaurantOverlayViewModel, List<DailyMenuItemOverlayViewModel>> data) {
    this.data = data;
    restaurantOverlayViewModels = new ArrayList<>(data.keySet());
  }

  @Override
  public int getSectionCount() {
    return restaurantOverlayViewModels.size();
  }

  @Override
  public int getItemCount(int section) {
    return data.get(restaurantOverlayViewModels.get(section)).size();
  }

  @Override
  public void onBindHeaderViewHolder(OverlayViewHolder holder, int section) {
    SectionOverlayViewHolder sectionOverlayViewHolder = (SectionOverlayViewHolder) holder;
    sectionOverlayViewHolder.fillView(restaurantOverlayViewModels.get(section));
  }

  @Override
  public void onBindViewHolder(OverlayViewHolder holder, int section, int relativePosition, int absolutePosition) {
    ItemOverlayViewHolder itemOverlayViewHolder = (ItemOverlayViewHolder) holder;
    itemOverlayViewHolder.fillView(data.get(restaurantOverlayViewModels.get(section)).get(relativePosition));
  }

  @Override
  public OverlayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_HEADER) {
      View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_daily_menu_overlay, parent, false);
      return new SectionOverlayViewHolder(headerView);
    } else {
      View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_menu_overlay, parent, false);
      return new ItemOverlayViewHolder(headerView);
    }
  }

  abstract class OverlayViewHolder extends RecyclerView.ViewHolder {

    public OverlayViewHolder(View itemView) {
      super(itemView);
    }
  }

  class SectionOverlayViewHolder extends OverlayViewHolder {

    @BindView(R.id.daily_menu_header_item_overlay_title)
    TextView headerTitle;

    public SectionOverlayViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(RestaurantOverlayViewModel restaurantOverlayViewModel) {
      headerTitle.setText(restaurantOverlayViewModel.title);
    }
  }

  class ItemOverlayViewHolder extends OverlayViewHolder {

    @BindView(R.id.daily_menu_item_overlay_image)
    ImageView itemImage;

    @BindView(R.id.daily_menu_item_overlay_title)
    TextView itemTitle;

    @BindView(R.id.daily_menu_item_overlay_price)
    TextView itemPrice;

    public ItemOverlayViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(DailyMenuItemOverlayViewModel dailyMenuItemOverlayViewModel) {
      Glide.with(itemImage.getContext())
          .load(dailyMenuItemOverlayViewModel.imageUrl)
          .centerCrop()
          .into(itemImage);
      itemTitle.setText(dailyMenuItemOverlayViewModel.title);
      itemPrice.setText(String.valueOf(dailyMenuItemOverlayViewModel.price));
    }
  }
}
