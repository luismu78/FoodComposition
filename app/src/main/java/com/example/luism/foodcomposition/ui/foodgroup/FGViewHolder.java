package com.example.luism.foodcomposition.ui.foodgroup;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luism.foodcomposition.R;

public class FGViewHolder extends RecyclerView.ViewHolder {
    private ViewGroup container;
    private TextView tvId;
    private TextView tvName;

    public FGViewHolder(View itemView) {
        super(itemView);

        container = (ViewGroup) itemView.findViewById(R.id.FGListItemContainer);
        tvId = (TextView) itemView.findViewById(R.id.tvId);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }

    public ViewGroup getContainer() {
        return container;
    }

    public TextView getTvId() {
        return tvId;
    }

    public TextView getTvName() {
        return tvName;
    }
}
