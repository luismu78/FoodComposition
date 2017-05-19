package com.example.luism.foodcomposition.ui.fooditem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luism.foodcomposition.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
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
        Log.d("HOLA", "position: " + position);

        final FoodValue fv = foodValues.get(position);

        holder.getTvName().setText(fv.getC_ori_name()); // TODO: lang
        holder.getTvQuantity().setText(fv.getBest_location() + " " + fv.getV_unit());
    }

    @Override
    public int getItemCount() {
        Log.d("HOLA", "foodValues.size(): " + foodValues.size());
        return foodValues.size();
    }
}
