package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.pojo.Food;

class FAdapter extends RecyclerView.Adapter<FViewHolder> {
    private List<Food> foodList;
    private FAdapterClickListener listener;

    interface FAdapterClickListener {
        void onListItemClicked(int id);
    }

    FAdapter(List<Food> foodList, FAdapterClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }


    @Override
    public FViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FViewHolder(inflater.inflate(R.layout.item_list_content, parent, false));
    }

    @Override
    public void onBindViewHolder(FViewHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.getTvId().setText(String.valueOf(food.getF_id()));
        holder.getTvName().setText(food.getF_ori_name());
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
