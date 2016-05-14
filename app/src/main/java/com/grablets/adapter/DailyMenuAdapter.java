package com.grablets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grablets.R;
import com.grablets.viewmodel.DailyMenuViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyMenuAdapter extends RecyclerView.Adapter<DailyMenuAdapter.DailyMenuViewHolder> {

  private List<DailyMenuViewModel.MenuItemViewModel> menuItemViewModels;

  public DailyMenuAdapter(List<DailyMenuViewModel.MenuItemViewModel> menuItemViewModels) {
    this.menuItemViewModels = menuItemViewModels;
  }

  @Override
  public DailyMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_menu, parent, false);
    return new DailyMenuViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DailyMenuViewHolder holder, int position) {
    holder.fillView(menuItemViewModels.get(position));
  }

  @Override
  public int getItemCount() {
    return menuItemViewModels.size();
  }

  class DailyMenuViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_menu_title)
    TextView itemTitle;
    @BindView(R.id.item_menu_description)
    TextView itemDescription;
    @BindView(R.id.item_image)
    ImageView itemImage;

    public DailyMenuViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(DailyMenuViewModel.MenuItemViewModel menuItemViewModel) {
      Glide.with(itemImage.getContext())
          .load(menuItemViewModel.imageUrl)
          .centerCrop()
          .into(itemImage);
      itemTitle.setText(menuItemViewModel.title);
      itemDescription.setText(menuItemViewModel.description);
    }
  }
}
