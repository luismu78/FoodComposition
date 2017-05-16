package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.luism.foodcomposition.R;

class FViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;

    FViewHolder(View itemView) {
        super(itemView);

        tvId = (TextView) itemView.findViewById(R.id.tvId);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }

    TextView getTvId() {
        return tvId;
    }

    TextView getTvName() {
        return tvName;
    }
}
