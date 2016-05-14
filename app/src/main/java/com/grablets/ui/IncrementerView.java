package com.grablets.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grablets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IncrementerView extends LinearLayout {

  @BindView(R.id.amount)
  TextView amount;

  private int currentAmount = 0;

  public IncrementerView(Context context) {
    this(context, null);
  }

  public IncrementerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IncrementerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.layout_incrementer, this);
    ButterKnife.bind(this, this);
  }

  @OnClick(R.id.increment_amount)
  protected void onIncrementClicked() {
    currentAmount++;
    amount.setText(String.valueOf(currentAmount));
  }

  @OnClick(R.id.decrement_amount)
  protected void onDecrementClicked() {
    if (currentAmount > 0) {
      currentAmount--;
      amount.setText(String.valueOf(currentAmount));
    }
  }

  public int getCurrentAmount(){
    return currentAmount;
  }
}
