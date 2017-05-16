package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luism.foodcomposition.R;

public class FDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvFoodItems;
    private TextView empty_view;

//    private F_ListItems items;
    private int id = 0;

    public FDetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the Items // es un int
            id = getArguments().getInt(ARG_ITEM_ID);
        }

        Log.d("HOLA", getClass().getSimpleName() + "  el id recibido es: " + id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        rvFoodItems = (RecyclerView) rootView.findViewById(R.id.rvFoodItems);
        empty_view = (TextView) rootView.findViewById(R.id.empty_view);

        return rootView;
    }
}
