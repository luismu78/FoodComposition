package com.example.luism.foodcomposition.ui.foodgroup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luism.foodcomposition.R;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FDetailActivity;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FDetailFragment;

import java.util.List;

class FGAdapter extends RecyclerView.Adapter<FGViewHolder> {
    private List<Food> foodGroupList;
    private Context context;

    FGAdapter(Context context, List<Food> foodGroupList) {
        this.context = context;
        this.foodGroupList = foodGroupList;
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
                Intent intent = new Intent(context, FDetailActivity.class);
                intent.putExtra(FDetailFragment.ARG_ITEM_ID, food.getFg_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodGroupList.size();
    }
}
