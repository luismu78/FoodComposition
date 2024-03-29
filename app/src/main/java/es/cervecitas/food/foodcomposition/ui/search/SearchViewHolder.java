package es.cervecitas.food.foodcomposition.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.food.foodcomposition.R;


class SearchViewHolder extends RecyclerView.ViewHolder {
    private TextView tvId;
    private TextView tvName;

    SearchViewHolder(View itemView) {
        super(itemView);

        tvId = (TextView) itemView.findViewById(R.id.tvId);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }

    TextView getTvId() {
        return tvId;
    }

    public TextView getTvName() {
        return tvName;
    }
}
