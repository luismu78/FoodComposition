package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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
import es.cervecitas.food.foodcomposition.pojo.Food;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGActivity;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;

public class FDetailActivity extends AppCompatActivity implements FView, FAdapter.FAdapterClickListener {

    public static final String ARG_ITEM_ID = "item_id";

    private int id = 0;

    @Inject
    FPresenter presenter;

    @BindView(R.id.rvFoodItems)
    RecyclerView rvFoodItems;

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
        setContentView(R.layout.activity_item_detail);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_ITEM_ID, 0);

        rvFoodItems.setAdapter(new FAdapter(new ArrayList<Food>(), this));
        rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
        rvFoodItems.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getFood(id);
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
            rvFoodItems.setAdapter(new FAdapter(new ArrayList<Food>(), this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvFoodItems.setAdapter(new FAdapter(listItems, this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        }
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
        Intent intent = new Intent(this, FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, FGActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
