package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGActivity;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;

public class FDetailActivity extends AppCompatActivity implements FView, FAdapter.FAdapterClickListener {

    public static final String ARG_ITEM_ID = "item_id";

    private int id = 0;

    @Inject
    FPresenter presenter;

    @BindView(R.id.rvFoodItems)
    RecyclerView rvFoodItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_ITEM_ID, 0);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle("HOLA CARABOLA");
        }

        rvFoodItems = (RecyclerView) findViewById(R.id.rvFoodItems);
        rvFoodItems.setAdapter(new FAdapter(this, new ArrayList<Food>(), this));
        rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
        rvFoodItems.setHasFixedSize(true);

        presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getFood(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cleanup();
    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        rvFoodItems.setAdapter(new FAdapter(this, listItems, this));
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
