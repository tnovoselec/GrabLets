package com.grablets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grablets.R;
import com.grablets.ui.IncrementerView;
import com.grablets.viewmodel.DailyMenuViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyMenuAdapter extends RecyclerView.Adapter<DailyMenuAdapter.DailyMenuViewHolder> {

  public interface MenuItemAmountListener {
    void onAmountChanged(String menuItemId, int newAmount);
  }

  private final List<DailyMenuViewModel.MenuItemViewModel> menuItemViewModels;
  private final MenuItemAmountListener menuItemAmountListener;
  private final Map<String, Integer> basketItems = new HashMap<>();

  public DailyMenuAdapter(List<DailyMenuViewModel.MenuItemViewModel> menuItemViewModels, MenuItemAmountListener menuItemAmountListener) {
    this.menuItemViewModels = menuItemViewModels;
    this.menuItemAmountListener = menuItemAmountListener;
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

  class DailyMenuViewHolder extends RecyclerView.ViewHolder implements IncrementerView.AmountListener {

    @BindView(R.id.item_menu_title)
    TextView itemTitle;
    @BindView(R.id.item_menu_description)
    TextView itemDescription;
    @BindView(R.id.item_menu_image)
    ImageView itemImage;
    @BindView(R.id.item_menu_incrementer)
    IncrementerView itemIncrementer;

    private DailyMenuViewModel.MenuItemViewModel menuItemViewModel;

    public DailyMenuViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fillView(DailyMenuViewModel.MenuItemViewModel menuItemViewModel) {
      this.menuItemViewModel = menuItemViewModel;
      Glide.with(itemImage.getContext())
          .load(menuItemViewModel.imageUrl)
          .centerCrop()
          .into(itemImage);
      itemTitle.setText(menuItemViewModel.title);
      itemDescription.setText(menuItemViewModel.description);
      itemIncrementer.setAmountListener(this);
      Integer amount = basketItems.get(menuItemViewModel.id);
      itemIncrementer.setAmount(amount == null ? 0 : amount);
    }

    @Override
    public void onAmountChanged(int amount) {
      if (menuItemAmountListener != null) {
        menuItemAmountListener.onAmountChanged(menuItemViewModel.id, amount);
      }
      basketItems.put(menuItemViewModel.id, amount);
    }
  }
}
