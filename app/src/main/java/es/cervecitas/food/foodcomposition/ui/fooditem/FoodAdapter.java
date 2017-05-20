package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.Utils;

class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private List<FoodValue> foodValues;
    private Context context;

    FoodAdapter(List<FoodValue> foodValues, Context context) {
        this.foodValues = foodValues;
        this.context = context;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new FoodViewHolder(inflater.inflate(R.layout.item_list_food_components, parent, false));
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        final FoodValue fv = foodValues.get(position);

        if (position == 0 || !foodValues.get(position - 1).getCg_descripcion().equals(fv.getCg_descripcion())) {
            holder.getListHeaderLayout().setVisibility(View.VISIBLE);
            holder.getListHeaderItem().setText(fv.getCg_descripcion());
        }

        holder.getTvName().setText(Utils.capitalizeFirstLetter(fv.getC_ori_name())); // TODO: lang
        String description = Utils.capitalizeFirstLetter(fv.getMu_descripcion());
        holder.getTvQuantity().setText(
                fv.getBest_location() + " "
                        + fv.getV_unit() + " "
                        + description);
    }

    @Override
    public int getItemCount() {
        return foodValues.size();
    }


}
