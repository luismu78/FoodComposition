package es.cervecitas.food.foodcomposition.ui.foodgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FDetailActivity;

public class FGActivity extends AppCompatActivity
        implements FGView, FGAdapter.FGAdapterClickListener {

    @Inject
    FGPresenter presenter;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

    // Error

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.imgNoData)
    ImageView imgNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @BindView(R.id.subNoData)
    TextView subNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

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
    protected void onStop() {
        super.onStop();

        presenter.cleanup();
    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        if (listItems.size() == 0) {
            showLoadingError();
            rvItemList.setAdapter(new FGAdapter(this, new ArrayList<Food>(), this));
            rvItemList.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvItemList.setAdapter(new FGAdapter(this, listItems, this));
            rvItemList.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void onListItemClicked(int id) {
        Intent intent = new Intent(this, FDetailActivity.class);
        intent.putExtra(FDetailActivity.ARG_ITEM_ID, id);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
//        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingError() {
        llNoData.setVisibility(View.VISIBLE);
        tvNoData.setText("Error leyendo del servidor");
        subNoData.setText("Inténtelo mas tarde");
    }

    @Override
    public void hideLoadingError() {
        llNoData.setVisibility(View.GONE);
    }

}
