package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;

class SeparatorViewHolder extends RecyclerView.ViewHolder {
    private TextView listHeaderItem;

    SeparatorViewHolder(View itemView) {
        super(itemView);

        listHeaderItem = (TextView) itemView.findViewById(R.id.listHeaderItem);
    }

    TextView getListHeaderItem() {
        return listHeaderItem;
    }
}
