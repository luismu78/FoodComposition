package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.cervecitas.food.foodcomposition.R;

class NutrientesDetailAdapter extends RecyclerView.Adapter<NutrienteDetailViewHolder> {
    private List<Food> foodList;
    private ClickListener listener;

    interface ClickListener {
        void onListItemClicked(int id);
    }

    NutrientesDetailAdapter(List<Food> foodList, ClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }

    @Override
    public NutrienteDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NutrienteDetailViewHolder(inflater.inflate(R.layout.item_list_nutriente_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(NutrienteDetailViewHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.getTvId().setText(Integer.toString(food.getF_id()));
        holder.getTvName().setText(food.getF_ori_name());
        holder.getTvAmount().setText(food.getBest_location() + food.getV_unit());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClicked(food.getF_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
