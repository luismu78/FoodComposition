package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luism.foodcomposition.R;
import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.ui.fooditem.FoodItemActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FDetailFragment extends Fragment implements FView, FAdapter.FAdapterClickListener {

    public static final String ARG_ITEM_ID = "item_id";

    private int id = 0;

    @Inject
    FPresenter presenter;

    @BindView(R.id.rvFoodItems)
    RecyclerView rvFoodItems;

    public FDetailFragment() {
        // empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((FoodCompositionApplication) getActivity().getApplication()).getAppComponent().inject(this);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            id = getArguments().getInt(ARG_ITEM_ID);

            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
            if (collapsingToolbarLayout != null) {
                collapsingToolbarLayout.setTitle("HOLA CARABOLA");
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        ButterKnife.bind(rootView);

        rvFoodItems = (RecyclerView) rootView.findViewById(R.id.rvFoodItems);
        rvFoodItems.setAdapter(new FAdapter(getContext(), new ArrayList<Food>(), this));
        rvFoodItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFoodItems.setHasFixedSize(true);

        presenter.setView(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getFood(id);
    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        rvFoodItems.setAdapter(new FAdapter(getContext(), listItems, this));
        rvFoodItems.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClearData() {

    }

    @Override
    public void showLoading() {
        Log.d("HOLA", "===== LOADING ====");
    }

    @Override
    public void hideLoading() {
        Log.d("HOLA", "===== LOADED ====");
    }

    @Override
    public void onListItemClicked(int id) {
        Intent intent = new Intent(getContext(), FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }
}
