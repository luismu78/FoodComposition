package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;

class NutrienteDetailViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;
    private TextView tvAmount;

    NutrienteDetailViewHolder(View itemView) {
        super(itemView);

        tvId = (TextView) itemView.findViewById(R.id.tvId);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
    }

    TextView getTvId() {
        return tvId;
    }

    TextView getTvName() {
        return tvName;
    }

    TextView getTvAmount() {
        return tvAmount;
    }
}
