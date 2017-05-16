package com.example.luism.foodcomposition.ui.foodgroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luism.foodcomposition.R;

import java.util.List;

class FGAdapter extends RecyclerView.Adapter<FGViewHolder> {
    private List<Food> foodGroupList;
    private Context context;
    private FGAdapterClickListener listener;

    interface FGAdapterClickListener {
        void onListItemClicked(int id);
    }

    FGAdapter(Context context, List<Food> foodGroupList, FGAdapterClickListener listener) {
        this.context = context;
        this.foodGroupList = foodGroupList;
        this.listener = listener;
    }

    @Override
    public FGViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new FGViewHolder(inflater.inflate(R.layout.item_list_content, parent, false));
    }

    @Override
    public void onBindViewHolder(FGViewHolder holder, int position) {
        final Food food = foodGroupList.get(position);
        holder.getTvName().setText(food.getFg_ori_name()); // TODO: lang
        holder.getTvId().setText(String.valueOf(food.getFg_id()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClicked(food.getFg_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodGroupList.size();
    }
}
