package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;

public class NutrientDetailActivity extends Activity implements NutrienteDetailView, NutrientesDetailAdapter.ClickListener {

    public static final String ARG_NUTRIENT_ID = "nutrientId";

    private int id = 0;

    @Inject
    NutrienteDetailPresenter presenter;

    @BindView(R.id.rvNutrientes)
    RecyclerView rvNutrientes;

    // Error

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.imgNoData)
    ImageView imgNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutriente_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_NUTRIENT_ID, 0);

        rvNutrientes.setAdapter(new NutrientesDetailAdapter(new ArrayList<Food>(), this));
        rvNutrientes.setLayoutManager(new LinearLayoutManager(this));
        rvNutrientes.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getFoodByNutrient(id);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.cleanUp();
    }

    @Override
    public void onDataLoaded(List<Food> foodList) {
        if (foodList.size() == 0) {
            showLoadingError();
            rvNutrientes.setAdapter(new NutrientesDetailAdapter(new ArrayList<Food>(), this));
            rvNutrientes.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvNutrientes.setAdapter(new NutrientesDetailAdapter(foodList, this));
            rvNutrientes.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void hideLoadingError() {

    }

    @Override
    public void onListItemClicked(int id) {
        Intent intent = new Intent(this, FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }
}
