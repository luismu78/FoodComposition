package com.example.luism.foodcomposition.ui.fooditem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.luism.foodcomposition.R;

public class FoodViewHolder extends RecyclerView.ViewHolder{
    private TextView tvName;
    private TextView tvQuantity;

    public FoodViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
    }

    public TextView getTvName() {
        return tvName;
    }

    public TextView getTvQuantity() {
        return tvQuantity;
    }
}
