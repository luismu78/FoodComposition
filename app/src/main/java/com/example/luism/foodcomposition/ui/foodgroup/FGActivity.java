package com.example.luism.foodcomposition.ui.foodgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.luism.foodcomposition.R;
import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FDetailActivity;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FDetailFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FGActivity extends AppCompatActivity implements FGView, FGAdapter.FGAdapterClickListener {

    @Inject
    FGPresenter presenter;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

//    @BindView(R.id.swipeContainer)
//    SwipeRefreshLayout swipeRefreshLayout;

    private boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }

        rvItemList.setAdapter(new FGAdapter(this, new ArrayList<Food>(), this));
        rvItemList.setLayoutManager(new LinearLayoutManager(this));
        rvItemList.setHasFixedSize(true);

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getFoodGroups();
    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        rvItemList.setAdapter(new FGAdapter(this, listItems, this));
        rvItemList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onListItemClicked(int id) {

        if (twoPane) {
            Bundle args = new Bundle();
            args.putInt(FDetailFragment.ARG_ITEM_ID, id);
            FDetailFragment fragment = new FDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, FDetailActivity.class);
            intent.putExtra(FDetailFragment.ARG_ITEM_ID, id);
            startActivity(intent);
        }
    }

    @Override
    public void onClearData() {

    }

    @Override
    public void showLoading() {
//        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        swipeRefreshLayout.setRefreshing(false);
    }

}
