package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;

public class NutrienteDetailViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;
    private TextView tvAmount;

    public NutrienteDetailViewHolder(View itemView) {
        super(itemView);

        tvId = (TextView) itemView.findViewById(R.id.tvId);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
    }

    public TextView getTvId() {
        return tvId;
    }

    public TextView getTvName() {
        return tvName;
    }

    public TextView getTvAmount() {
        return tvAmount;
    }
}
