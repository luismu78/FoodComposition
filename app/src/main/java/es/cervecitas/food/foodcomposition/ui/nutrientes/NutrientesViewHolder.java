package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;

class NutrientesViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;

    NutrientesViewHolder(View itemView) {
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
