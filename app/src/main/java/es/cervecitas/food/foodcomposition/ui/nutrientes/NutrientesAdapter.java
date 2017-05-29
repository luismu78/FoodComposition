package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.pojo.Nutrient;

class NutrientesAdapter extends RecyclerView.Adapter<NutrientesViewHolder> {
    private List<Nutrient> nutrientList;
    private ClickListener listener;

    interface ClickListener {
        void onListItemClicked(int id);
    }

    NutrientesAdapter(List<Nutrient> nutrientList, ClickListener listener) {
        this.nutrientList = nutrientList;
        this.listener = listener;
    }

    @Override
    public NutrientesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NutrientesViewHolder(inflater.inflate(R.layout.item_list_nutriente, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(NutrientesViewHolder holder, int position) {
        final Nutrient nutrient = nutrientList.get(position);
        holder.getTvId().setText(Integer.toString(nutrient.getC_id()));
        holder.getTvName().setText(nutrient.getC_ori_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClicked(nutrient.getC_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nutrientList.size();
    }
}
