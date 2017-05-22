package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;


class FoodTitleViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName;


    FoodTitleViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }

    public TextView getTvName() {
        return tvName;
    }
}
