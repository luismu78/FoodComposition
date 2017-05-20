package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;


class FoodViewHolder extends RecyclerView.ViewHolder{
    private TextView tvName;
    private TextView tvQuantity;
    private LinearLayout listHeaderLayout;
    private TextView listHeaderItem;

    FoodViewHolder(View itemView) {
        super(itemView);

        listHeaderLayout = (LinearLayout) itemView.findViewById(R.id.listHeaderLayout);
        listHeaderItem = (TextView) itemView.findViewById(R.id.listHeaderItem);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
    }

    public TextView getTvName() {
        return tvName;
    }

    TextView getTvQuantity() {
        return tvQuantity;
    }

    public LinearLayout getListHeaderLayout() {
        return listHeaderLayout;
    }

    public TextView getListHeaderItem() {
        return listHeaderItem;
    }
}
