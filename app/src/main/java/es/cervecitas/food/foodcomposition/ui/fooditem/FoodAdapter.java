package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.Utils;

class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SEPARATOR = 1;
    private static final int VIEW_TYPE_FOOD = 2;

    private List<FoodValue> foodValues;
    private List<Object> recyclerViewContents;

    FoodAdapter(List<FoodValue> foodValues) {
        this.foodValues = foodValues;

        prepareFoodValues();
    }

    private void prepareFoodValues() {
        recyclerViewContents = new ArrayList<>();
        String ant = "";

        for (FoodValue fv : foodValues) {
            if ((!fv.getCg_descripcion().equals(ant)) && !fv.getCg_descripcion().equals("Proximales")) {
                ant = fv.getCg_descripcion();
                recyclerViewContents.add(ant);
            }
            recyclerViewContents.add(fv);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_SEPARATOR:
                return new SeparatorViewHolder(inflater.inflate(R.layout.item_list_group_separator, parent, false));
            case VIEW_TYPE_FOOD:
                return new FoodViewHolder(inflater.inflate(R.layout.item_list_food_components, parent, false));
            default:
                return new FoodViewHolder(inflater.inflate(R.layout.item_list_food_components, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SEPARATOR:
                String data = (String) recyclerViewContents.get(position);
                ((SeparatorViewHolder) holder).getListHeaderItem().setText(data);
                break;
            case VIEW_TYPE_FOOD:
                FoodValue fv = ((FoodValue) recyclerViewContents.get(position));
                ((FoodViewHolder) holder).getTvName().setText(Utils.capitalizeFirstLetter(fv.getC_ori_name()));
                String description = Utils.capitalizeFirstLetter(fv.getMu_descripcion());
                ((FoodViewHolder) holder).getTvQuantity()
                        .setText(fv.getBest_location() + " " + fv.getV_unit() + " " + description);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return foodValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (recyclerViewContents.get(position).getClass().equals(FoodValue.class)) {
            return VIEW_TYPE_FOOD;
        }

        return VIEW_TYPE_SEPARATOR;
    }

}
