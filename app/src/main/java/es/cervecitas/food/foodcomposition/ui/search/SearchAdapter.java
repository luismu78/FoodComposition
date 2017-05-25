package es.cervecitas.food.foodcomposition.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private List<es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food> foodList;
    private Context context;
    private SearchAdapterClickListener listener;

    public interface SearchAdapterClickListener {
        void onSearchItemClicked(int id);
    }

    public SearchAdapter(Context context, List<Food> foodList, SearchAdapterClickListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new SearchViewHolder(inflater.inflate(R.layout.item_list_content, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.getTvId().setText(String.valueOf(food.getF_id()));
        holder.getTvName().setText(food.getF_ori_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSearchItemClicked(food.getF_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}